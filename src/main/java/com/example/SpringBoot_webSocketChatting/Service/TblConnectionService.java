package com.example.SpringBoot_webSocketChatting.Service;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.SpringBoot_webSocketChatting.Entity.TblConnection;
import com.example.SpringBoot_webSocketChatting.Repository.TblConnectionRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TblConnectionService {

	@Autowired
	TblConnectionRepository tblConnectionRepository;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void saveTblConnection(TblConnection tblConnection)
	{
		tblConnectionRepository.save(tblConnection);
	}

	public TblConnection findBySessionId(String sessionId)
	{
		return tblConnectionRepository.findBySessionId(sessionId);
	}
}
