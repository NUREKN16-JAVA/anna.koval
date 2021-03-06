package usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import org.hsqldb.Database;

import ua.nure.kn.koval.usermanagement.User;

class HsqldbUserDao implements UserDao {

	private static final String SELECT_ALL_QUERY = "SELECT id, firstName, lastName, dateofBirth from users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstName, lastName, dateofBirth) VALUES (?,?,?)";
	private static final String UPDATE_QUERY = "Update users Set firstName=?, lastName=?, dateofBirth=? Where id=?";
	private static final String DELETE_QUERY = "Delete From users Where id=?";
	private static final String SELECT_BY_ID = "SELECT id, firstName, lastName, dateofBirth FROM users WHERE id=?";

	private ConnectionFactory connectionFactory;
	
	public HsqldbUserDao() {

	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory=connectionFactory;
	}
	
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}



	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}



	@Override
	public User create(User user) throws DatabaseException {
		try {
			Connection connection =connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1,  user.getFirstName());
			statement.setString(2,  user.getLastName());
			statement.setDate(3,  new Date(user.getDateOfBirth().getTime()));
			int n=statement.executeUpdate();
			if (n!=1) {
				throw new DatabaseException("Number of the inserted rows: " + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys =callableStatement.executeQuery();
			if (keys.next()) {
				user.setId(new Long(keys.getLong(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void update(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            statement.setLong(4, user.getId().longValue());
            if (statement.executeUpdate() != 1)
                throw new DatabaseException("Number of updated rows: " + statement.executeUpdate());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

	}

	@Override
	public void delete(User user) throws DatabaseException {
		 try {
	            Connection connection = connectionFactory.createConnection();
	            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
	            statement.setLong(1, user.getId().longValue());
	            if (statement.executeUpdate() != 1)
	                throw new DatabaseException("Number of deleted rows: " + statement.executeUpdate());
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            throw new DatabaseException(e);
	        }

	}

	@Override
	public User find(Long id) throws DatabaseException {
		 try {
	            Connection connection = connectionFactory.createConnection();
	            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
	            statement.setLong(1, id.longValue());
	            ResultSet resultSet = statement.executeQuery();
	            if (!resultSet.next())
	                throw new DatabaseException("Can't find user id: " + id);
	            User result = new User();
	            result.setId(new Long(resultSet.getLong(1)));
	            result.setFirstName(resultSet.getString(2));
	            result.setLastName(resultSet.getString(3));
	            result.setDateOfBirth(resultSet.getDate(4));
	            return result;
	        } catch (SQLException e) {
	            throw new DatabaseException(e);
	        }
	}

	@Override
	public Collection findAll() throws DatabaseException {
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet= statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				User user=new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return result;
	}

}
