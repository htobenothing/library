package biz;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.ItemsDAO;
import dto.Items;

public class ItemsManager {

	ItemsDAO itmdao = DAOFactory.getItemsDAO();

	public int createItems(Items itm) {
		try {
			return itmdao.createItems(itm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Items getOneItems(int i) {
		try {
			return itmdao.getOneItems(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updateItems(Items itm) {
		try {
			return itmdao.updateItems(itm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<Items> getAllItems() {

		try {
			return itmdao.getAllItems();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Items> searchItemByStatus(String itemstatus) {
		try {
			return itmdao.searchItemByStatus(itemstatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Items> searchItemsByTitle(String title) {

		try {
			return itmdao.searchItemByTitle(title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Items> searchItemsByItemType(int itemtypeID) {

		try {
			return itmdao.searchItemByItemType(itemtypeID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Items> searchItembyTitleStatus(String title, String itemstatus) {

		try {
			return itmdao.searchItembyTitleStatus(title, itemstatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Items> searchItembyTitleItemType(String title, int itemTypeID) {

		try {
			return itmdao.searchItembyTitleItemType(title, itemTypeID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Items> searchItembyStatusItemType(String itemstatus, int itemTypeID) {
		System.out.println("before try");

		try {
			System.out.println("go try");
			return itmdao.searchItembyStatusItemType(itemstatus, itemTypeID);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Items> searchItembyStatusItemNumber(String itemstatus, int itemNumber) {
		System.out.println("before try");

		try {
			System.out.println("go try");
			return itmdao.searchItembyStatusItemNumber(itemstatus, itemNumber);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Items> searchItemsByFullCriteria(String title, int itemTypeID, String itemstatus) {

		try {
			return itmdao.searchItemsByFullCriteria(title, itemTypeID, itemstatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
