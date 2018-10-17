package com.work.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.work.manager.entity.User;
import com.work.manager.service.UserService;
import com.work.manager.util.Result;

@Controller
@RequestMapping(path="user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="getAll",method=RequestMethod.GET)
	@ResponseBody
	public Result<List<User>> getAll(){
		Result<List<User>> result = new Result<List<User>>();
		
		result.setContent(userService.getAll());
		return result;
	}
}
