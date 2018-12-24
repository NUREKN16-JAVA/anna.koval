package ua.nure.kn.koval.usermanagement.db;

import java.util.Collection;

import ua.nure.kn.koval.usermanagement.User;

public interface UserDao {
	User create(User user) throws DatabaseException;
	void update(User user) throws DatabaseException;
	void delete(User user) throws DatabaseException;
	User find(Long id) throws DatabaseException;
	Collection findAll() throws DatabaseException;
	static void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub
		
	}
}
