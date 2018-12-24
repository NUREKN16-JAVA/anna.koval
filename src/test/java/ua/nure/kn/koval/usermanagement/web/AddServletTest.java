package ua.nure.kn.koval.usermanagement.web;

import java.text.DateFormat;
import java.util.Date;

import ua.nure.kn.koval.usermanagement.User;

public class AddServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	public void testAdd() {
		Date date = new Date();
		User newUser = new User("John", "Doe", date);
		User user = new User(new Long(1000), "John", "Doe", date);
		getMockUserDao().expectAndReturn("create", newUser, user);

		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance());
		addRequestParameter("okButton", "Ok");
		doPost();
	}

	private void addRequestParameter(String string, DateFormat dateInstance) {
		// TODO Auto-generated method stub
	}

	public void testAddEditEmptyLastName() {
		Date date = new Date();
		addRequestParameter("firstName", "John");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	public void testAddEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	public void testAddEmptyDate() {
		Date date = new Date();
		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	public void testAddEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", "aklefkb");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
}
