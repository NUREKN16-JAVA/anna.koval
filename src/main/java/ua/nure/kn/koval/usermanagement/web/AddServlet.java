package ua.nure.kn.koval.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.koval.usermanagement.User;
import ua.nure.kn.koval.usermanagement.db.DaoFactory;
import ua.nure.kn.koval.usermanagement.db.DatabaseException;

public class AddServlet extends EditServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.nure.kn.koval.usermanagement.web.EditServlet#showPage(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.nure.kn.koval.usermanagement.web.EditServlet#processUser(ua.nure.kn.koval.
	 * usermanagement.User)
	 */
	@Override
	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().create(user);
	}

}
