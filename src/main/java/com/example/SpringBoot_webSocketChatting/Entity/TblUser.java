package com.example.SpringBoot_webSocketChatting.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class TblUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seqId")
    private Long seqId;

    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "userName")
    private String userName;
}
