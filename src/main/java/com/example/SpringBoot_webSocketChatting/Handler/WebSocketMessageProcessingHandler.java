package com.example.SpringBoot_webSocketChatting.Handler;

import com.example.SpringBoot_webSocketChatting.Entity.TblBetRoleInfo;
import com.example.SpringBoot_webSocketChatting.Entity.TblBettingHistory;
import com.example.SpringBoot_webSocketChatting.Entity.TblUser;
import com.example.SpringBoot_webSocketChatting.EntityForm.BetRole;
import com.example.SpringBoot_webSocketChatting.Event.WebSocketMessageEvent;
import com.example.SpringBoot_webSocketChatting.Service.TblBetRoleInfoService;
import com.example.SpringBoot_webSocketChatting.Service.TblBettingHistoryService;
import com.example.SpringBoot_webSocketChatting.Service.TblUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Async
@Component
@Slf4j
public class WebSocketMessageProcessingHandler implements ApplicationListener<WebSocketMessageEvent> {

    @Autowired
    private TblBetRoleInfoService tblBetRoleInfoService;

    @Autowired
    private TblUserService tblUserService;

    @Autowired
    private TblBettingHistoryService tblBettingHistoryService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onApplicationEvent(WebSocketMessageEvent event) {
        log.info("세션: {}", event.getSessionId());
        log.info("메시지: {}", event.getMessage());

        try {
            Message message = objectMapper.readValue(event.getMessage(), Message.class);

            log.info("TYPE: {}", message.getType());
            if (message.getType().equals(Message.MessageType.ROLE))
            {
                try {
                    LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) message.getData();

                    String tableName = String.valueOf(linkedHashMap.get("table_name"));
                    Integer roleNo = (Integer) linkedHashMap.get("role_no");
                    String betRole = String.valueOf(linkedHashMap.get("bet_role"));

                    String bet_role = "";
                    List<BetRole> betRoles = objectMapper.readValue(betRole, new TypeReference<List<BetRole>>() {});

                    for(int index = 0; index < betRoles.size(); index ++) {
                        BetRole r = betRoles.get(index);
                        String temp = String.format("%s:%s-%s:%s-%s:%d:%d\n",
                                r.getBetAmount(),
                                r.getBefore_p_wins(),
                                r.getP_bet_role(),
                                r.getBefore_b_wins(),
                                r.getB_bet_role(),
                                r.getWin_next_step(),
                                r.getLoss_next_step()
                        );
                        bet_role += temp;
                    }

                    tblBetRoleInfoService.saveTblBetRoleInfo(TblBetRoleInfo.builder()
                            .sessionId(event.getSessionId())
                            .tableName(tableName)
                            .roleNo(roleNo)
                            .betRole(bet_role)
                            .createdAt(new Date())
                            .build()
                    );
                } catch (Exception e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            else if (message.getType().equals(Message.MessageType.NEW)) {
                LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) message.getData();

                String userName = String.valueOf(linkedHashMap.get("user_name"));

                tblUserService.saveTblUser(TblUser.builder()
                        .sessionId(event.getSessionId())
                        .userName(userName)
                        .build()
                );
            }
            else if (message.getType().equals(Message.MessageType.HISTORY)) {
                LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) message.getData();

                String roomId = String.valueOf(linkedHashMap.get("room_id"));
                Integer roleNo = (Integer) linkedHashMap.get("role_no");
                Integer betAmount = (Integer) linkedHashMap.get("bet_amount");
                Integer betStep = (Integer) linkedHashMap.get("bet_step");
                String betResult = String.valueOf(linkedHashMap.get("bet_result"));
                Integer betProfit = (Integer) linkedHashMap.get("bet_profit");
                String betTime = String.valueOf(linkedHashMap.get("bet_time"));
                String winLoss = String.valueOf(linkedHashMap.get("win_loss"));

                tblBettingHistoryService.saveBettingHistory(TblBettingHistory.builder()
                        .sessionId(event.getSessionId())
                        .tableName(roomId)
                        .roleNo(roleNo)
                        .betAmount(betAmount)
                        .betStep(betStep)
                        .betResult(betResult)
                        .betProfit(betProfit)
                        .betTime(betTime)
                        .winLoss(winLoss)
                        .createdAt(new Date())
                        .build()
                );
            }
            else {
                log.info("error ");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
