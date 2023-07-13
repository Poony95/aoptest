package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

	@GetMapping("/listEmp")
	public String list() {
		return "사원목록";
	}
}
