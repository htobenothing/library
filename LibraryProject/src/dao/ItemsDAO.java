package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.Items;

public interface ItemsDAO {
	
	public abstract int createItems(Items itm) throws SQLException;
	
	//get item to update by librarian
	public abstract Items getOneItems(int i) throws SQLException;
	
	public abstract int updateItems(Items itm) throws SQLException;
	
	//get list of items based on (available/unavailable/onloan) status by librarian
	public abstract ArrayList<Items> getItemByStatus(int i) throws SQLException;	
	
	public abstract ArrayList<Items> getAllItems() throws SQLException;
	
	//get list of items by input title
	public abstract ArrayList<Items> searchItemByTitle(String title)throws SQLException;
	
	//get list of items by input title with selected item type (Book, CD, etc..) by student
	public abstract ArrayList<Items> searchItemsByCriteria(String title, int i) throws SQLException;
	

}
