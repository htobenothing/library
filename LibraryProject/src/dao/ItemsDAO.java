package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.Items;

public interface ItemsDAO {
	
	public abstract int createItems(Items itm) throws SQLException;
	public abstract int updateItems(Items itm) throws SQLException;
	
	//get item to update by librarian
	public abstract Items getOneItems(int i) throws SQLException;	
	public abstract ArrayList<Items> getAllItems() throws SQLException;		
	
	
	
	//SEARCHING
	//get items list by status
	public abstract ArrayList<Items> searchItemByStatus(String itemstatus) throws SQLException;
	//get items list by title
	public abstract ArrayList<Items> searchItemByTitle(String title)throws SQLException;	
	//get items list by itemType
	public abstract ArrayList<Items> searchItemByItemType(int itemtypeID)throws SQLException;
	//get items list by title and status
	public abstract ArrayList<Items> searchItembyTitleStatus(String title, String itemstatus) throws SQLException;
	//get items list by title and item type
	public abstract ArrayList<Items> searchItembyTitleItemType(String title, int itemtypeID) throws SQLException;
	//get items list by status and item type
	public abstract ArrayList<Items> searchItembyStatusItemNumber(String itemstatus, int itemNumber) throws SQLException;
	//get items list by status and item type
	public abstract ArrayList<Items> searchItembyStatusItemType(String itemstatus, int itemtypeID) throws SQLException;
	//get items list by title, item type, status
	public abstract ArrayList<Items> searchItemsByFullCriteria(String title, int itemTypeID, String itemstatus) throws SQLException;

}
