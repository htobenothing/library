package user.dao;

import java.util.Date;

import user.dto.User;
import user.exception.UserNotFoundException;

public interface UserDAO
{

	public User findUserById(int userID)
		throws UserNotFoundException;
	
	public java.util.Collection findUsersByRole(String role);
	
	
	public void deleteUser(int memberID)
		throws UserNotFoundException;
	

	public User createUser(int userID, String userName, String password, String userStatus, Date dateOfBirth, String address,
			String email, String phone, String role, Date createdDate);
	

	public void updateUser(int userID, String userName, String password, String userStatus, Date dateOfBirth, String address,
			String email, String phone, String role, Date createdDate)
		throws UserNotFoundException;
		
	
	public void close();
	

	public boolean isClosed();
}
