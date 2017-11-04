package test;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.intellect.controller.UserController;
import com.intellect.model.Response;
import com.intellect.model.User;


public class TestUserController {
	
	private UserController testController;
	
	@Test(dataProvider="Users")
	public void testCreateUser(String id,String fName,String lName,String email,
			int pinCode,String birthDate,boolean isActive){
		
		User user = new User(id,fName,lName,email,pinCode,birthDate,isActive);
		Response response = testController.createUser(user);
		Assert.assertEquals(response.getMessage(), user.getId());
		
	}

	@DataProvider(name = "Users")
	public static Object[][] users() {
	 
	        return new Object[][] { 
	        	{ "1", "Test1","lname","email1@gmail.com",123456,"25-JAN-1990",true }, 
	        	{ "2", "Test2","lname","email2@gmail.com",123456,"25-MAR-2020",true },
	        	{ "3", "Test3","lname","email3@gmail.com",123456,"25-NOV-1990",true },
	        	{ "4", "Test4","lname","email4@gmail.com",123456,"25-DEC-2018",true },
	        	};
	 
	  }
}
