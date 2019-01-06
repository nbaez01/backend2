package com.empresa.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.app.entity.User;
import com.empresa.app.service.UserService;
import com.empresa.app.util.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	@Autowired
	protected UserService userService;
	
	protected ObjectMapper mapper;
	
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public RestResponse saveOrUpdate(@RequestBody String userJson) throws JsonParseException,JsonMappingException,IOException {
		this.mapper= new ObjectMapper();
		User user = this.mapper.readValue(userJson, User.class);
		
		if(!this.validate(user)) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),"Los campos obligatorios no estan diligenciados");
		}
		
		this.userService.save(user);
		
		return new RestResponse(HttpStatus.OK.value(),"Operacion exitosa");
	}
	
	private boolean validate(User user) {
		boolean isValid = true;
		if(user.getFirstName()==null) {
			isValid=false;
		}
		
		if(user.getFirstSurname()==null) {
			isValid=false;
		}
		
		if(user.getAddress()==null) {
			isValid=false;
		}
		
		return isValid;
	}
}
