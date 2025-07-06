package com.telusko.FirstSpringWebApp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.telusko.FirstSpringWebApp.model.Employee;

@Controller
public class EmployeeController
{
	@GetMapping("/register")
	public String getHomePage(@ModelAttribute("emp")Employee emp)
	{
		return "register";
	}
//	public String register(Map<String, Object>map, HttpServletRequest req)
//	{
//		Strimg name=req.getParameter("name")
//		return "response";
//	}
	
	@PostMapping("/register")
	public String register(Map<String, Object>map, @ModelAttribute("emp")Employee emp)
	{
		System.out.println(emp);
		map.put("emp", emp);
		return "response";
	}
	
	

}
