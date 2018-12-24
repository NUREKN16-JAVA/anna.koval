package ua.nure.kn.koval.usermanagement.db;

import junit.framework.TestCase;
import ua.nure.kn.koval.usermanagement.db.DaoFactory;
import ua.nure.kn.koval.usermanagement.db.UserDao;

public class DaoFactoryTest extends TestCase {

	public void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null", daoFactory);
			UserDao userDao= DaoFactory.getUserDao();
			assertNotNull("UserDao instance is null", userDao);
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
