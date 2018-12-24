package ua.nure.kn.koval.usermanagement.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import ua.nure.kn.koval.usermanagement.User;
import ua.nure.kn.koval.usermanagement.db.DaoFactory;
import ua.nure.kn.koval.usermanagement.db.DatabaseException;

public class EditServlet extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("okButton") != null) {
			doOk(req, resp);
		} else if (req.getParameter("cancelButton") != null) {
			doCancel(req, resp);
		} else {
			showPage();
		}
	}

	private void showPage() {
		// TODO Auto-generated method stub

	}

	private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/browse").forward(req, resp);

	}

	private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = null;
		try {
			user = getUser(resp);
		} catch (ValidationException e1) {
			req.setAttribute("error", e1.getMessage());
			showPage(req, resp);
			return;
		}
		try {
			processUser(user);
		} catch (DatabaseException e) {
			e.printStackTrace();
			new ServletException(e);
		}
		req.getRequestDispatcher("/browse").forward(req, resp);

	}

	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/edit.jsp").forward(req, resp);

	}

	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().update(user);

	}

	private User getUser(HttpServletResponse req) throws ValidationException {
		User user = new User();
		String idStr = ((ServletRequest) req).getParameter("id");
		String firstName = ((ServletRequest) req).getParameter("firstName");
		String lastName = ((ServletRequest) req).getParameter("lastName");
		String dateStr = ((ServletRequest) req).getParameter("date");

		if (firstName == null) {
			throw new ValidationException("First name is empty");
		}

		if (lastName == null) {
			throw new ValidationException("Last name is empty");
		}

		if (dateStr == null) {
			throw new ValidationException("Date is empty");
		}

		if (idStr != null) {
			user.setId(new Long(idStr));
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			user.setDateOfBirth(DateFormat.getDateInstance().parse(dateStr));
		} catch (ParseException e) {
			throw new ValidationException("Date format is incorrect");
		}

		return user;

	}
}