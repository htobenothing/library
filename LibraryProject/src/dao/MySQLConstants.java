package dao;

public class MySQLConstants {
	
	public static final String URL = "jdbc:mysql://localhost/library";
    public static final String USER = "root";
    public static final String PASSWORD = "password";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    public static final String SELECT_QRY = "SELECT * FROM ";
    public static final String INSERT_QRY = "INSERT INTO ";
    public static final String UPDATE_QRY = "UPDATE ";
    
    public static final String ITEMS_TABLE_NAME = "library.items ";
    public static final String ITEMS_COL_ITEMNUMBER = "itemNumber";
    public static final String ITEMS_COL_TITLE = "title";
    public static final String ITEMS_COL_AUTHOR = "author";
    public static final String ITEMS_COL_PUBLISHER = "publisher";
    public static final String ITEMS_COL_YEAR = "year";
    public static final String ITEMS_COL_DESCRIPTION = "description";
    public static final String ITEMS_COL_ISBN = "isbn";
    public static final String ITEMS_COL_ITEMTYPEID = "itemtypeID";
    public static final String ITEMS_COL_ITEMSTATUS = "itemstatus";

}
