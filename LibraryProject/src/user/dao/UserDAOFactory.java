
package user.dao;

import user.util.UserConstants;

public final class UserDAOFactory
{
	private UserDAOFactory()
	{
		// this constructor is intentionally made private
	}

	public static UserDAO getUserDAO(int database)
	{
		if (database==UserConstants.DB_MYSQL)
			return new UserDAOMySQL();
		//else if (database==UserConstants.DB_ORACLE)
			//return new UserDAOOracle();
		else
			return new UserDAOMySQL();
	}
}
