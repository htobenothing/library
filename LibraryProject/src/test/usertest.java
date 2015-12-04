package test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserDataAccess;
import dto.User;

public class usertest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User ur = new User();
		UserDataAccess uda= new UserDataAccess();
		
		try {
			uda.OpenConnection();
			ArrayList<User> ulist =(ArrayList<User>) uda.findAll();
			for (User user : ulist) {
				System.out.println(user.toString());
			}
		uda.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		try {
			uda.OpenConnection();
			User ud = uda.findByUserId("S00000003");
			System.out.println(ud.toString());
			User us =new User("S00000007","Same6","asdf","student","1",Date.valueOf("1989-01-01"),"blk23","Same4@gmail.com","12345678",Date.valueOf("2004-04-02"),0);
			uda.createNewUser(us);
			uda.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
