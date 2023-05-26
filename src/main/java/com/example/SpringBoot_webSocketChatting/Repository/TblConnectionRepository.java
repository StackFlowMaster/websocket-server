package com.example.SpringBoot_webSocketChatting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SpringBoot_webSocketChatting.Entity.TblConnection;

@Repository
public interface TblConnectionRepository extends JpaRepository<TblConnection, Long> {

    @Query("SELECT conn FROM TblConnection conn WHERE conn.sessionId = :sessionId")
    TblConnection findBySessionId(@Param("sessionId") String sessionId);
}
