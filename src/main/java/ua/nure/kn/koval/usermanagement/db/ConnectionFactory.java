package ua.nure.kn.koval.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection() throws DatabaseException;
}
