package com.example.SpringBoot_webSocketChatting.Repository;

import com.example.SpringBoot_webSocketChatting.Entity.TblBlockIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblBlockIpRepository extends JpaRepository<TblBlockIp, Long> {

}
