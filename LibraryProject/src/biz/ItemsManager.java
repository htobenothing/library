package biz;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.ItemsDAO;
import dto.Items;

public class ItemsManager {
	
	ItemsDAO itmdao = DAOFactory.getItemsDAO();
	
	public int createItems(Items itm){
		try {
			return itmdao.createItems(itm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public Items getOneItems(int i){
		try {
			return itmdao.getOneItems(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateItems(Items itm){
		try {
			return itmdao.updateItems(itm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public ArrayList<Items> getItemByStatus(int i) {
		try {
			return itmdao.getItemByStatus(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	public ArrayList<Items> searchItemsByTitle(String title){
		
		try {
			return itmdao.searchItemByTitle(title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Items> searchItemsByCriteria(String title, int i) {
		
		try {
			return itmdao.searchItemsByCriteria(title, i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
