package com.example.SpringBoot_webSocketChatting.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_betrole_info")
public class TblBetRoleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seqId")
    private Long seqId;

    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "tableName")
    private String tableName;

    @Column(name = "roleNo")
    private int roleNo;

    @Column(name = "betRole")
    private String betRole;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createdAt")
    private Date createdAt;
}
