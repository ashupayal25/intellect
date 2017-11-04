package com.intellect.util; 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.intellect.model.User;

public class ValidateUtil {
	
	private static String SUCCESS="success";
	private static String USER_ALREADY_EXIST="user_already_exist";

	public static String validateUser(User user){
		if(StringUtils.isEmpty(user.getfName())){
			return "FirstName cannot be empty";
		}else if(StringUtils.isEmpty(user.getEmail())){
			return "Email cannot be empty";
		}else if(StringUtils.isEmpty(user.getPinCode())){
			return "Pincode cannot be empty";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");

		Date date = new Date();
		try {
			date = sdf.parse(user.getBirthDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(new Date().after(date)){
			return "Date is future date";
		}
		return SUCCESS;
	}

	public static boolean checkIfUserExist(User user,List<User> userList) {
	
		return userList.stream().anyMatch(t->t.getEmail().equalsIgnoreCase(user.getEmail()));
		
		
	}
}
