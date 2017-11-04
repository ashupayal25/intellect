package com.intellect.model;

import java.util.ArrayList;
import java.util.List;

public class UserCollection {
	
	private static List<User> users;
	
	private UserCollection(){
		
	}
	
	public static List<User> getList(){
		if(users==null){
			users=new ArrayList<>();
		}
		return users;
	}
	
	

}
