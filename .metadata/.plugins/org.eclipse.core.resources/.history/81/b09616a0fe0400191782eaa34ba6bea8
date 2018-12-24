package ua.nure.kn.koval.usermanagement.web;

import java.util.Properties;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ua.nure.kn.koval.usermanagement.db.DaoFactory;
import ua.nure.kn.koval.usermanagement.db.MockDaoFactory;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock  mockUserDao;
	
	/**
	 * @return the mockUserDao
	 */
	public Mock getMockUserDao() {
		return mockUserDao;
	}

	/**
	 * @param mockUserDao the mockUserDao to set
	 */
	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
	}

	protected void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}

}
