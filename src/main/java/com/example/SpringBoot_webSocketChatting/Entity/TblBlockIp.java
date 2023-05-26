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
@Table(name = "tbl_block_ip")
public class TblBlockIp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seqId")
    private Long seqId;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column(name = "reason")
    private String reason;

    @Column(name = "blockStatus")
    private Integer blockStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createdAt")
    private Date createdAt;
}
