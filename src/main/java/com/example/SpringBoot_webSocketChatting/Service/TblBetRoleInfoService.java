package com.example.SpringBoot_webSocketChatting.Service;

import com.example.SpringBoot_webSocketChatting.Entity.TblBetRoleInfo;
import com.example.SpringBoot_webSocketChatting.Repository.TblBetRoleInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TblBetRoleInfoService {

    @Autowired
    TblBetRoleInfoRepository tblBetRoleInfoRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveTblBetRoleInfo(TblBetRoleInfo tblBetRoleInfo)
    {
        tblBetRoleInfoRepository.save(tblBetRoleInfo);
    }
}
