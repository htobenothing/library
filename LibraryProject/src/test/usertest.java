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
		
	/*	try {
			
			ArrayList<User> ulist =(ArrayList<User>) uda.findAll();
			for (User user : ulist) {
				System.out.println(user.toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		
/*		try {
			uda.OpenConnection();
			User ud = uda.findByUserId("S00000010");
			System.out.println(ud.toString());
			if(ud.getUserId()==null){
				System.out.println("no record");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*try {
			uda.OpenConnection();
			User us =new User("S00000007","Same8","asdf","student","1",Date.valueOf("1989-01-01"),"blk23","Same4@gmail.com","12345678",Date.valueOf("2004-04-02"),0);
			
			uda.createNewUser(us);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			uda.OpenConnection();
			ArrayList<User> slist= (ArrayList<User>) uda.finByName("james");
			for (User user : slist) {
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		int i = Integer.parseInt("abc");
		
		
	}

}
