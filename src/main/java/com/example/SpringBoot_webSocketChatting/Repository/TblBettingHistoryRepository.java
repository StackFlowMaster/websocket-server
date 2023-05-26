package com.example.SpringBoot_webSocketChatting.Repository;

import com.example.SpringBoot_webSocketChatting.Entity.TblBettingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblBettingHistoryRepository extends JpaRepository<TblBettingHistory, Long> {

}
