package com.example.SpringBoot_webSocketChatting.Service;

import com.example.SpringBoot_webSocketChatting.Entity.TblBlockIp;
import com.example.SpringBoot_webSocketChatting.Repository.TblBlockIpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TblBlockIpService {

    @Autowired
    TblBlockIpRepository tblBlockIpRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveTblBlockIp(TblBlockIp tblBlockIp)
    {
        tblBlockIpRepository.save(tblBlockIp);
    }
}
