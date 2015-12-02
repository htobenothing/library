package dao;

public class DAOFactory {
	
	public static ItemsDAO getItemsDAO(){
		ItemsDAO itmDAO = new ItemsDataAccess();
		return itmDAO;
	}

}
