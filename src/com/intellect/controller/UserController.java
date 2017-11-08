package com.intellect.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intellect.model.Response;
import com.intellect.model.User;
import com.intellect.model.UserCollection;
import com.intellect.util.ValidateUtil;

@RestController
public class UserController {

	@RequestMapping(value="/create",method = RequestMethod.GET,headers="Accept=application/json")
	 public Response createUser(@RequestBody User user) {
	 
		
		String output=null;
		if(ValidateUtil.checkIfUserExist(user,UserCollection.getList())){
			return new Response("USEr already exist", user.getId());
		}
		
		output = ValidateUtil.validateUser(user);
		if(!output.equalsIgnoreCase("SUCCESS")){
			return new Response(output, user.getId());
		}
		
		UserCollection.getList().add(user);
		return new Response("Success",user.getId());
	 }
	
	@RequestMapping(value="/update/{userId}", method = RequestMethod.GET,headers="Accept=application/json")
	 public Response updateUser(@PathVariable String userId,@RequestBody User user) {
		List<User> userList = UserCollection.getList();
		
		for (User temp : userList) {
			if(temp.getId().equalsIgnoreCase(userId)){
				temp.setBirthDate(user.getBirthDate());
				temp.setActive(user.isActive());
				temp.setEmail(user.getEmail());
				temp.setfName(user.getfName());
				temp.setlName(user.getlName());
				temp.setPinCode(user.getPinCode());
				user.setActive(false);
			}
			
		}
		
	  return null;
	 } 
	
	@RequestMapping(value="delete/{userId}", method = RequestMethod.GET,headers="Accept=application/json")
	public Response deleteUser(@PathVariable String userId){
		
		List<User> userList = UserCollection.getList();
		userList.stream().anyMatch(t->t.getId().equalsIgnoreCase(userId));
		
		for (User user : userList) {
			if(user.getId().equalsIgnoreCase(userId)){
				user.setActive(false);
			}
			
		}
		
		return new Response("Success",userId);
		
	}
}
