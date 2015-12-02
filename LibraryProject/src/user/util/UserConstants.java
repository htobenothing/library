package user.util;

public final class UserConstants
{
	static public final String LIBRARY_DATASOURCE_NAME = "java:comp/env/jdbc/LibraryDBXA";
	
	static public final String USER_TABLE_NAME = "USER";
	
	static public final int USER_ROLE_LIBRARIAN = 1;
	static public final int USER_ROLE_STUDENT = 2;
		
	static public final int DB_MYSQL = 1;
	static public final int DB_MSSQL = 2;
	static public final int DB_ORACLE = 3;
		
	private UserConstants()
	{
		// this constructor is intentionally private 
	}
}
