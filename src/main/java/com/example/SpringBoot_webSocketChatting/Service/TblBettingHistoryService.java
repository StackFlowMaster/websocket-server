package com.example.SpringBoot_webSocketChatting.Service;

import com.example.SpringBoot_webSocketChatting.Entity.TblBettingHistory;
import com.example.SpringBoot_webSocketChatting.Repository.TblBettingHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TblBettingHistoryService {

    @Autowired
    TblBettingHistoryRepository tblBettingHistoryRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveBettingHistory(TblBettingHistory tblBettingHistory)
    {
        tblBettingHistoryRepository.save(tblBettingHistory);
    }
}
