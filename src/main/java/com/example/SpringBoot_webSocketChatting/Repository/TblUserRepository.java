package com.example.SpringBoot_webSocketChatting.Repository;

import com.example.SpringBoot_webSocketChatting.Entity.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblUserRepository extends JpaRepository<TblUser, Long> {
}
