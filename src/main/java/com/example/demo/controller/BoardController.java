
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// responseBody와 Controller가 합쳐진 어드바이스
@RestController
public class BoardController {

	@GetMapping("/listBoard")
	public String list() {
		return "게시물 목록";
	}
}
