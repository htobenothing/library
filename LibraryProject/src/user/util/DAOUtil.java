
package user.util;

import user.dto.User;
import user.exception.*;

import java.sql.*;
import java.util.Collection;
import javax.sql.*;
import javax.transaction.UserTransaction;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.logging.*;

import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.log.LogFactory;


public final class DAOUtil
{
	static final private Log log = LogFactory.getLog(DAOUtil.class);
	
	private DAOUtil()
	{
		// this constructor is intentionally private
	}

	public static Connection openJDBCConnection()
	{
		 Connection conn = null;
		 
		 DataSource ds = null;
		 
		 try
		 {
			Object obj = lookup(UserConstants.LIBRARY_DATASOURCE_NAME);
			ds = (DataSource) narrow(obj, DataSource.class); 
		 	
		 	conn = ds.getConnection();
		 }
		 catch (SQLException ex)
		 {
		 	throw new DAORuntimeException(ex);
		 }
		 
		 
		 return conn;
	}
	
	public static void closeJDBCConnection(Connection conn)
	{
		if (conn != null)
		{
			 try
			 {
				conn.close();
			 }
			 catch (SQLException ex)
			 {
				log.error(conn, ex);
			 }
		}
	}

	public static void closeStatement(Statement stmt)
	{
		if (stmt != null)
		{
			 try
			 {
				stmt.close();
			 }
			 catch (SQLException ex)
			 {
				log.error(stmt, ex);
			 }
		}
	}

	public static void closeResultSet(ResultSet rs)
	{
		if (rs != null)
		{
			 try
			 {
				rs.close();
			 }
			 catch (SQLException ex)
			 {
				log.error(rs, ex);
			 }
		}
	}


	static public Object lookup(String name)
	{
		Context ctx = getInitialContext();
		
		Object result = null;
		
		try
		{
			result = ctx.lookup(name);
		}
		catch (NamingException ex)
		{
			throw new DAORuntimeException(ex);
		}
		
		return result;
	}
	
	static public Context getInitialContext()
	{
		Context ctx = null;

		try
		{
			ctx = new InitialContext();
		}
		catch (NamingException ex)
		{
			throw new DAORuntimeException(ex);
		}		
		return ctx; 
	}
	
	static public Object narrow(Object obj, Class clazz)
	{
		return javax.rmi.PortableRemoteObject.narrow(obj, clazz);
	}

}
