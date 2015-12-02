
package user.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import user.dto.User;
import user.exception.*;
import user.util.DAOUtil;
import user.util.UserConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class UserDAOMySQL implements UserDAO
{
	static final private Log log = LogFactory.getLog(User.class);
	
	
	
	public UserDAOMySQL()
	{
		
	}
	
	public User findUserById( int userID)
		throws UserNotFoundException
	{
		if (userID == 0)
		{
			throw new UserNotFoundException("userID is invalid");
		}

		Connection conn = DAOUtil.openJDBCConnection();
		
		User result = null;
		ResultSet rs = null;
		PreparedStatement stmtSelect = null;
		
		try
		{
			StringBuffer sbSelect = new StringBuffer();
			
			sbSelect.append("SELECT ");
			sbSelect.append("userID, userName, password, userStatus, dateOfBirth, address,email, phone, role, createdDate ");
			sbSelect.append("FROM ");
			sbSelect.append(UserConstants.USER_TABLE_NAME);
			sbSelect.append(" WHERE userID = ?");
			
			stmtSelect = conn.prepareStatement(sbSelect.toString());
			
			stmtSelect.setInt(1, userID);
						
			rs = stmtSelect.executeQuery();
			Collection<User> c = makeUserObjectsFromResultSet(rs);
			
			if (c.size() != 1)
			{
				throw new UserNotFoundException("id = " + userID);
			}
			
			Iterator<User> iter = c.iterator();
			
			result = (User) iter.next();      
		}
		catch (SQLException ex)
		{
			log.error(ex);
			throw new DAORuntimeException(ex);
		}
		finally
		{
			DAOUtil.closeStatement(stmtSelect);
			DAOUtil.closeResultSet(rs);
			DAOUtil.closeJDBCConnection(conn);
		}
		
		return result;
	}
	
	public java.util.Collection findUsersByRole( String role)
	{
		if (role == null)
		{
			throw new NullPointerException("role parameter");
		}
		
		Connection conn = DAOUtil.openJDBCConnection();
		
		Collection<User> result = null;
		
		ResultSet rs = null;
		PreparedStatement stmtSelect = null;
		
		try
		{
			StringBuffer sbSelect = new StringBuffer();
			
			sbSelect.append("SELECT memberID, name, dateofbirth, address, email, contactnumber, role, createdate FROM ");
			sbSelect.append(UserConstants.USER_TABLE_NAME);
			sbSelect.append(" WHERE role = ?");
			
			stmtSelect = conn.prepareStatement(sbSelect.toString());
			
			stmtSelect.setString(1, role);
			
			rs = stmtSelect.executeQuery();
			
			result = makeUserObjectsFromResultSet(rs);

		}
		catch (SQLException ex)
		{
			log.error(ex);
			throw new DAORuntimeException(ex);
		}
		finally
		{
			DAOUtil.closeStatement(stmtSelect);
			DAOUtil.closeResultSet(rs);
			DAOUtil.closeJDBCConnection(conn);
		}
		
		return result;
	}
	
	public void deleteUser( int userID)
		throws UserNotFoundException
	{
		if (userID == 0)
		{
			throw new NullPointerException("userID parameter");
		}
		
		Connection conn = DAOUtil.openJDBCConnection();
		
		PreparedStatement stmtDelete = null;
		
		try
		{
			StringBuffer sbDelete = new StringBuffer();
			
			sbDelete.append("DELETE FROM ");
			sbDelete.append(UserConstants.USER_TABLE_NAME);
			sbDelete.append(" WHERE memberID = ?");
			
			stmtDelete = conn.prepareStatement(sbDelete.toString());
			
			stmtDelete.setInt(1, userID);
			
			int rows = stmtDelete.executeUpdate();
			
			if (rows != 1)
			{
				throw new SQLException(
					"executeUpdate return value: "
					+ rows);
			}
			
		}
		catch (SQLException ex)
		{
			log.error(ex);
			throw new DAORuntimeException(ex);
		}
		finally
		{
			DAOUtil.closeStatement(stmtDelete);
			DAOUtil.closeJDBCConnection(conn);
		}
	}
	
	public User createUser(int userID, String userName, String password, String userStatus, Date dateOfBirth, String address,
			String email, String phone, String role, Date createdDate)
	{
			
		if (userID ==0)
		{
			throw new NullPointerException("name parameter");
		}
		if (userName == null)
		{
			throw new NullPointerException("dateofbirth parameter");
		}
		
		User result = null;
		PreparedStatement stmtInsert = null;

		Connection conn = DAOUtil.openJDBCConnection();

		try
		{
			
			
			StringBuffer sbInsert = new StringBuffer();
			
			sbInsert.append("INSERT INTO ");
			sbInsert.append(UserConstants.USER_TABLE_NAME);
			sbInsert.append(" ( memberID, name, dateofbirth, address, email, contactnumber, role, createdate ) ");
			sbInsert.append(" VALUES (");
			sbInsert.append("?, ?, ?, ?, ?, ?, ?, ?) ");
			
			stmtInsert = conn.prepareStatement(sbInsert.toString());
			
			stmtInsert.setString(1, userName);
			stmtInsert.setString(2, password);
			stmtInsert.setDate(3, new java.sql.Date(dateOfBirth.getTime()));
			stmtInsert.setString(4, address);
			stmtInsert.setString(5, email);
			stmtInsert.setString(6, phone);
			stmtInsert.setString(7, userStatus);
			stmtInsert.setDate(8, new java.sql.Date(new Date().getTime()));
			
			log.info("About to execute INSERT: values "
				+ userID
				+ ", "
				+ userName
				+ ", "
				+ dateOfBirth);
				
			int rows = stmtInsert.executeUpdate();

			if (rows != 1)
			{
				throw new SQLException(
					"executeUpdate return value: "
					+ rows);
			}
			
		}
		
		catch (SQLException ex)
		{
			log.error(ex);
			throw new DAORuntimeException(ex);
		}
		finally
		{
			DAOUtil.closeStatement(stmtInsert);
			DAOUtil.closeJDBCConnection(conn);
		}
		return result;
	}

	public void updateUser(int userID, String userName, String password, String userStatus, Date dateOfBirth, String address,
			String email, String phone, String role, Date createdDate) throws UserNotFoundException
	{
		if (userID == 0)
		{
			throw new NullPointerException("userID parameter");
		}
		
		if (userName == null)
		{
			throw new NullPointerException("name parameter");
		}
		if (dateOfBirth == null)
		{
			throw new NullPointerException("dateofbirth parameter");
		}

		Connection conn = DAOUtil.openJDBCConnection();
		
		PreparedStatement stmtUpdate = null;
		
		try
		{
			StringBuffer sbUpdate = new StringBuffer();
			
			sbUpdate.append("UPDATE ");
			sbUpdate.append(UserConstants.USER_TABLE_NAME);
			sbUpdate.append(" SET ");
			sbUpdate.append(" name = ?, ");
			sbUpdate.append(" dateofbirth = ?, ");
			sbUpdate.append(" email = ? ");
			sbUpdate.append(" WHERE memberID = ?");
			
			stmtUpdate = conn.prepareStatement(sbUpdate.toString());

			stmtUpdate.setString(1, userName);
			stmtUpdate.setDate(2, new java.sql.Date(dateOfBirth.getTime()));
			stmtUpdate.setString(3, email);
			stmtUpdate.setInt(4, userID);
			
			
			int rows = stmtUpdate.executeUpdate();
			
			if (rows != 1)
			{
				throw new SQLException(
					"executeUpdate return value: "
					+ rows);
			}
			
		}
		catch (SQLException ex)
		{
			throw new DAORuntimeException(ex);
		}
		finally
		{
			DAOUtil.closeStatement(stmtUpdate);
			DAOUtil.closeJDBCConnection(conn);
		}
	}
	
	public void close()
	{
		log.info("close() called");
		
	}
	
	
	
	private Collection<User> makeUserObjectsFromResultSet(ResultSet rs)
			throws java.sql.SQLException
		{
			Collection<User> result = new java.util.ArrayList();
			
	
			while (rs.next())
			{
				int memberID = rs.getInt("userID"); 	
				String name = rs.getString("userName"); 	
				Date dateofbirth = rs.getDate("dateofbirth"); 	
				String address = rs.getString("address");
				String email = rs.getString("email");
				String contactnumber = rs.getString("contactnumber");
				String role = rs.getString("role");
				Date createdate = rs.getDate("createdate");

				
				User m = new User(memberID, name, 
						role, role, dateofbirth, address, email,
						contactnumber, null, createdate);
				result.add(m); 	
			}
			
			return result;
		} 
	
	private int getUniqueUserIdSequence( Connection conn)
			throws java.sql.SQLException
		{
			
			if (null == conn)
			{
				throw new NullPointerException("conn parameter");
			}

			if (conn.isClosed())
			{
				throw new IllegalArgumentException(
						"connection is closed");
			}

			int id = 0;
			
			Statement stmtSelect = null;
			ResultSet rs = null;
			
			StringBuffer sbSelect = new StringBuffer();
			
			sbSelect.append("SELECT max(user_id) max_id");

			sbSelect.append(" FROM "+UserConstants.USER_TABLE_NAME);
			
			try
			{
				stmtSelect = conn.createStatement();
				rs = stmtSelect.executeQuery(sbSelect.toString());
				rs.next();
				id = rs.getInt("max_id")+1;
				
			}
			finally
			{
				DAOUtil.closeStatement(stmtSelect);
				DAOUtil.closeResultSet(rs);
			}
		
			return id;
		}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}
		
	
}
