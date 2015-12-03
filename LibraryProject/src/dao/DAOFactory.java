package dao;

public class DAOFactory {
	
	public static ItemsDAO getItemsDAO(){
		ItemsDAO itmDAO = new ItemsDataAccess();
		return itmDAO;
	}
	public static TransactionDao geTransactionDao()
	{
		return new TransactionDataAccess();
	}
	public static UserDAO getUserDAO() {
		return new UserDataAccess();
	}
	

}
