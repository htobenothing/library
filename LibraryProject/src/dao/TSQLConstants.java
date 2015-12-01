package dao;

import java.sql.Date;

public class TSQLConstants {
	public static final String URL = "jdbc:mysql://localhost/library";
    public static final String USER = "root";
    public static final String PASSWORD = "password";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    public static final String TRANSACTION_TABLE_NAME = "library.transaction";
    public static final String TRANSACTION_COL_TRANSACTIONDI="transactionID";
    public static final String TRANSACTION_COL_USERID="userID";
    public static final String TRANSACTION_COL_ITEAMNUMBER="itemNumber";
    public static final String TRANSACTION_COL_STATUS="status";
    public static final String TRANSACTION_COL_LOANDATE="loanDate";
    public static final String TRANSACTION_COL_DUEDATE="duedate";
    public static final String TRANSACTION_COL_RETURNDATE="returnDate";
    	
}
