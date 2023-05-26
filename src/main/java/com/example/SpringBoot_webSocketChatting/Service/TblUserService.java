package com.example.SpringBoot_webSocketChatting.Service;

import com.example.SpringBoot_webSocketChatting.Entity.TblUser;
import com.example.SpringBoot_webSocketChatting.Repository.TblUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TblUserService {

    @Autowired
    TblUserRepository tblUserRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveTblUser(TblUser tblUser)
    {
        tblUserRepository.save(tblUser);
    }
}
