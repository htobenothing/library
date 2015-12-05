package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import dto.User;

public interface UserDAO {
	public abstract User findByUserId(String id) throws SQLException;
	public abstract Collection<User> findAll() throws SQLException;
	public abstract int createNewUser(User u) throws SQLException;
	public abstract Collection<User> finByName(String name) throws SQLException;
	public abstract Collection<User> findStudents() throws SQLException;
	public abstract int update(User u) throws SQLException;
}
