package ua.nure.kn.koval.usermanagement.db;

public class DaoFactoryImpl extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		UserDao result =null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
		    result = (UserDao) clazz.newInstance();
		    UserDao.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
