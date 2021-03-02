package com.monCRUD.servlets;

import java.io.IOException;
import java.util.List;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monCRUD.model.User;
import com.monCRUD.mongodata.UserDAO;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class CrudServlets
 */
@WebServlet("/CrudServlets")
public class CrudServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		
		MongoClient mongo = (MongoClient) request.getServletContext()
				.getAttribute("MONGO_CLIENT");
		UserDAO userDAO = new UserDAO(mongo);
		User u = new User();
		u.setId(id);
		u = userDAO.readUser(u);
		request.setAttribute("user", u);
		List<User> users = userDAO.readAllUser();
		request.setAttribute("users", users);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		
			User u = new User();
			u.setCountry(country);
			u.setName(name);
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			UserDAO userDAO = new UserDAO(mongo);
			userDAO.createUser(u);
			
			List<User> users = userDAO.readAllUser();
			request.setAttribute("users", users);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		
		MongoClient mongo = (MongoClient) request.getServletContext()
				.getAttribute("MONGO_CLIENT");
		UserDAO userDAO = new UserDAO(mongo);
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setCountry(country);
		userDAO.updateUser(u);
	
		List<User> users = userDAO.readAllUser();
		request.setAttribute("users", users);

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		
		MongoClient mongo = (MongoClient) request.getServletContext()
				.getAttribute("MONGO_CLIENT");
		UserDAO userDAO = new UserDAO(mongo);
		User u = new User();
		u.setId(id);
		userDAO.deleteUser(u);

		List<User> users = userDAO.readAllUser();
		request.setAttribute("users", users);

		
	}

}
