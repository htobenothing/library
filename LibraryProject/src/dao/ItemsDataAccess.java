package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Items;

public class ItemsDataAccess implements ItemsDAO {
	
	private static Connection connection;
	private static Statement statement;
	private ResultSet rs;
	
	
	private Connection openConnection() {
		try {
			Class.forName(MySQLConstants.DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(MySQLConstants.URL, MySQLConstants.USER, MySQLConstants.PASSWORD);
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}
	
	private static void closeConnection() {
		try {
			statement.close();

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	@Override
	public int createItems(Items itm) throws SQLException {
		openConnection();		
		String ins = MySQLConstants.INSERT_QRY + MySQLConstants.ITEMS_TABLE_NAME + "(title, author, publisher, year, description, isbn, itemtypeID, itemstatus) VALUES ("
				+ "\"" + itm.getTitle() 
				+ "\",\"" + itm.getAuthor() + "\",\"" + itm.getPublisher()
				+ "\",\"" + itm.getYear() + "\",\"" + itm.getDescription() 
				+ "\",\"" + itm.getIsbn() + "\",\""	+ itm.getItemtypeID()
				+ "\",\"" + itm.getItemstatus()+ "\");";
		int result = statement.executeUpdate(ins);
		closeConnection();
		return result;
	}

	@Override
	public int updateItems(Items itm) throws SQLException {
		openConnection();
		int i = statement.executeUpdate("UPDATE library.items SET title = " 
				+ "\"" + itm.getTitle()
				+ "\", author =" + "\"" + itm.getAuthor()
				+ "\", publisher = " + "\"" + itm.getPublisher() 
				+ "\", year = " + "\"" + itm.getYear()
				+ "\", description = " + "\"" + itm.getDescription()
				/*+ "\", isbn = " + "\"" + itm.getIsbn()*/
				/*+ "\", itemtypeID = " + itm.getItemtypeID()*/
				+ "\", itemstatus = " + "'" + itm.getItemstatus()
				+ "' WHERE itemNumber = " + itm.getItemNumber() + ";");
		
		closeConnection();
		return i;
	}	
	
	@Override
	public Items getOneItems(int itemNumber) throws SQLException {		
		openConnection();
		Items itm = new Items();
		rs = statement.executeQuery("SELECT * FROM library.items WHERE itemNumber ="
				+ itemNumber + ";");
		while (rs.next()) {
			itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
		}
		closeConnection();
		return itm;
	}

	@Override
	public ArrayList<Items> getAllItems() throws SQLException {
		openConnection();
		ArrayList<Items> result = new ArrayList<Items>();
		rs = statement.executeQuery("SELECT * FROM library.items;");
		while (rs.next()) {
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}	
	
//	SELECT * FROM library.items WHERE title LIKE '%man%';
	@Override
	public ArrayList<Items> searchItemByTitle(String title) throws SQLException {
		openConnection();
		ArrayList<Items> result = new ArrayList<Items>();		
		String qry = MySQLConstants.SELECT_QRY + MySQLConstants.ITEMS_TABLE_NAME 
										+ "WHERE title LIKE '%" + title + "%'" ;
		
		rs = statement.executeQuery(qry);
		while(rs.next()){
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}
	
	@Override
	public ArrayList<Items> searchItemByStatus(String itemstatus) throws SQLException {
		openConnection();
		ArrayList<Items> result = new ArrayList<Items>();		
		String qry = MySQLConstants.SELECT_QRY + MySQLConstants.ITEMS_TABLE_NAME 
										+ "WHERE itemstatus = '" + itemstatus + "'" ;
		
		rs = statement.executeQuery(qry);
		while(rs.next()){
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}
	@Override
	public ArrayList<Items> searchItemByItemType(int itemtypeID) throws SQLException {
		openConnection();
		ArrayList<Items> result = new ArrayList<Items>();		
		String qry = MySQLConstants.SELECT_QRY + MySQLConstants.ITEMS_TABLE_NAME 
										+ "WHERE itemtypeID = " + itemtypeID ;
		
		rs = statement.executeQuery(qry);
		while(rs.next()){
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}	
	
	@Override
	public ArrayList<Items> searchItembyTitleStatus(String title, String itemstatus) throws SQLException {
		openConnection();
		ArrayList<Items> result = new ArrayList<Items>();		
		String qry = MySQLConstants.SELECT_QRY + MySQLConstants.ITEMS_TABLE_NAME 
										+ "WHERE title LIKE '%" + title + "%' AND itemstatus = '" + itemstatus + "'" ;
		
		rs = statement.executeQuery(qry);
		while(rs.next()){
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}

	@Override
	public ArrayList<Items> searchItembyTitleItemType(String title, int itemtypeID) throws SQLException {
		openConnection();
		ArrayList<Items> result = new ArrayList<Items>();		
		String qry = MySQLConstants.SELECT_QRY + MySQLConstants.ITEMS_TABLE_NAME 
										+ "WHERE title LIKE '%" + title + "%' AND itemtypeID = " 
										+ itemtypeID ;
		
		rs = statement.executeQuery(qry);
		while(rs.next()){
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}
	@Override
	public ArrayList<Items> searchItembyStatusItemType(String itemstatus, int itemtypeID) throws SQLException {
		System.out.println("before open");
		openConnection();System.out.println("open");
		ArrayList<Items> result = new ArrayList<Items>();		
		String qry = MySQLConstants.SELECT_QRY + MySQLConstants.ITEMS_TABLE_NAME 
										+ "WHERE itemstatus = '" + itemstatus + "' AND itemtypeID = " 
										+ itemtypeID ;
		System.out.println(qry);
		
		rs = statement.executeQuery(qry);
		while(rs.next()){
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}
	
	@Override
	public ArrayList<Items> searchItembyStatusItemNumber(String itemstatus, int itemNumber) throws SQLException {
		System.out.println("before open");
		openConnection();System.out.println("open");
		ArrayList<Items> result = new ArrayList<Items>();		
		String qry = MySQLConstants.SELECT_QRY + MySQLConstants.ITEMS_TABLE_NAME 
										+ "WHERE itemstatus = '" + itemstatus + "' AND itemNumber = " 
										+ itemNumber ;
		System.out.println(qry);
		
		rs = statement.executeQuery(qry);
		while(rs.next()){
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}
	
	//SELECT * FROM library.items WHERE title="title" and itemtypeID = 2 AND itemstatus='0';
	@Override
	public ArrayList<Items> searchItemsByFullCriteria(String title, int itemtypeID, String itemstatus)
			throws SQLException {
		openConnection();
		ArrayList<Items> result = new ArrayList<Items>();		
		String qry = MySQLConstants.SELECT_QRY + MySQLConstants.ITEMS_TABLE_NAME 
										+ "WHERE title LIKE '%" + title + "%' AND itemtypeID = " 
										+ itemtypeID + " AND itemstatus = '" + itemstatus + "'" ;
		
		rs = statement.executeQuery(qry);
		while(rs.next()){
			Items itm = new Items(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
					rs.getInt(8),rs.getString(9));
			result.add(itm);
		}
		closeConnection();
		return result;
	}


}
