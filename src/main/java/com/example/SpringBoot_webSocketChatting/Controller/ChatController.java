package com.example.SpringBoot_webSocketChatting.Controller;

import com.example.SpringBoot_webSocketChatting.Common.Result;
import com.example.SpringBoot_webSocketChatting.Service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;

@Controller
@Slf4j
public class ChatController {

	@Autowired
	private WebSocketService webSocketService;

	@GetMapping("/")
	public String loginGet() {
		return "index";
	}

	@PostMapping("/process")
	@ResponseBody
	public Result<JSONObject> memberProcess(@RequestParam("sessionId") String sessionId,
											@RequestParam("method") String method,
											HttpServletRequest httpServletRequest)
	{
		log.info("memberProcess >>>>>>>>>>> ");
		Result<JSONObject> result = new Result<>();

		log.info("sessionId [{}]", sessionId);
		log.info(" method [{}]", method);

		try {
			webSocketService.sendMessage(sessionId, method);
			result.success("Success");
		} catch (Exception e) {
			result.error500("Internal Error: " + e.getMessage());
			log.error("Internal error: " + e.getMessage());
		}

		return result;
	}
}
