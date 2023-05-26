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
@Table(name = "tbl_connection")
public class TblConnection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seqId")
	private Long seqId;

	@Column(name = "sessionId")
	private String sessionId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "connectionDate")
	private Date connectionDate;

	@Column(name = "connectionIp")
	private String connectionIp;

	@Column(name = "connectionStatus")
	private Integer connectionStatus;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "createdAt")
	private Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updatedAt")
	private Date updatedAt;

//	public TblConnection(String sessionId, String connectionIp, Integer connectionStatus)
//	{
//		this.sessionId = sessionId;
//		this.connectionIp = connectionIp;
//		this.connectionStatus = connectionStatus;
//	}
}
