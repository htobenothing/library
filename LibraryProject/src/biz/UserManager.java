package biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import dao.UserDAO;
import dto.User;

public class UserManager {
	
	UserDAO userAdoInt= dao.DAOFactory.getUserDAO();
	
	public ArrayList<User> getAllUser(){
		try {
			return (ArrayList<User>) userAdoInt.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public User getOneUser(String id){
		try {
			return userAdoInt.findByUserId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int createUser(User ur){
		try {
			return userAdoInt.createNewUser(ur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public Collection<User> getUserByName(String name){
		try {
			return userAdoInt.finByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
