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
@Table(name = "tbl_betting_history")
public class TblBettingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seqId")
    private Long seqId;

    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "tableName")
    private String tableName;

    @Column(name = "roleNo")
    private Integer roleNo;

    @Column(name = "betAmount")
    private Integer betAmount;

    @Column(name = "betStep")
    private Integer betStep;

    @Column(name = "betResult")
    private String betResult;

    @Column(name = "betProfit")
    private Integer betProfit;

    @Column(name = "betTime")
    private String betTime;

    @Column(name = "winLoss")
    private String winLoss;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createdAt")
    private Date createdAt;
}
