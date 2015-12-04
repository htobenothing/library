package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import dto.User;


public class UserDataAccess implements UserDAO {
	
	private static Connection con;
	private static Statement statement;
	private static ResultSet rs;
	private static PreparedStatement prstmt;
	
	public static void OpenConnection(){
		try {
			Class.forName(MySQLConstants.DRIVER_CLASS);
			con = DriverManager.getConnection(MySQLConstants.URL, MySQLConstants.USER, MySQLConstants.PASSWORD);
			statement =con.createStatement();
			prstmt=null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	public static void CloseConnection(){
		try {
			con.close();
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public User findByUserId(String userid) throws SQLException {
		OpenConnection();
		User user = new User();
		String sel = "Select * from library.user where userid=?";
		prstmt =con.prepareStatement(sel);
		prstmt.setString(1, userid);
		rs = prstmt.executeQuery();
		while(rs.next()){
			user =new User(rs.getString(1),rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getDate(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getDate(10),rs.getInt(11));
		}
		CloseConnection();
		return user;
		
		
	}
	
	public Collection<User> findAll() throws SQLException {
		OpenConnection();
		String sel ="Select * from library.user";
		rs = statement.executeQuery(sel);
		ArrayList<User> ulist = new ArrayList<User>();
		while(rs.next()){
			User u = new User();
			u.setUserId(rs.getString(1));
			u.setUserName(rs.getString(2));
			u.setPassword(rs.getString(3));
			u.setRole(rs.getString(4));
			u.setUserStatus(rs.getString(5));
			u.setDateOfBirth(rs.getDate(6));
			u.setAddress(rs.getString(7));
			u.setEmail(rs.getString(8));
			u.setPhoneNumber(rs.getString(9));
			u.setCreateDate(rs.getDate(10));
			u.setOnloanNumber(rs.getInt(11));
			ulist.add(u);
		}
		CloseConnection();
		return ulist;
		
	}
	
	public int createNewUser(User u) throws SQLException{
		OpenConnection();
		String ins ="insert into library.user values(?,?,?,?,?,?,?,?,?,?,?)";
		prstmt =con.prepareStatement(ins);
		prstmt.setString(1, u.getUserId());
		prstmt.setString(2, u.getUserName());
		prstmt.setString(3, u.getPassword());
		prstmt.setString(4, u.getRole());
		prstmt.setString(5, u.getUserStatus());
		prstmt.setDate(6, u.getDateOfBirth());
		prstmt.setString(7, u.getAddress());
		prstmt.setString(8, u.getEmail());
		prstmt.setString(9, u.getPhoneNumber());
		prstmt.setDate(10, u.getCreateDate());
		prstmt.setInt(11, u.getOnloanNumber());
		int i = prstmt.executeUpdate();
		CloseConnection();
		return i;
		
	}
	@Override
	public Collection<User> finByName(String name) throws SQLException {
		OpenConnection();
		String selname = "Select * from library.user where username like ?";
		ArrayList<User> uslist= new ArrayList<User>();
		prstmt = con.prepareStatement(selname);
		prstmt.setString(1, "%"+name+"%");
		rs =prstmt.executeQuery();
		while(rs.next()){
			User u = new User();
			u.setUserId(rs.getString(1));
			u.setUserName(rs.getString(2));
			u.setPassword(rs.getString(3));
			u.setRole(rs.getString(4));
			u.setUserStatus(rs.getString(5));
			u.setDateOfBirth(rs.getDate(6));
			u.setAddress(rs.getString(7));
			u.setEmail(rs.getString(8));
			u.setPhoneNumber(rs.getString(9));
			u.setCreateDate(rs.getDate(10));
			u.setOnloanNumber(rs.getInt(11));
			uslist.add(u);
		}
		return uslist;
	}
	
	
	

}
