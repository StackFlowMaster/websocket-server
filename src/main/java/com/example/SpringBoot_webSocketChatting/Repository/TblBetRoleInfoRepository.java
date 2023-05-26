package com.example.SpringBoot_webSocketChatting.Repository;

import com.example.SpringBoot_webSocketChatting.Entity.TblBetRoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblBetRoleInfoRepository extends JpaRepository<TblBetRoleInfo, Long> {

}
