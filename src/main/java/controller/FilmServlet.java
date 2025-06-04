package controller;
import jakarta.servlet.RequestDispatcher;
import dataaccess.ConnectionProperty;
import dataaccess.FilmDbDAO;
import domain.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
* Servlet implementation class DirectorServlet_
*/
@WebServlet("/film")
public class FilmServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
ConnectionProperty prop;

 /**
 * @see HttpServlet#HttpServlet()
 */
 public FilmServlet()  throws FileNotFoundException, IOException {
	 super();
	// TODO Auto-generated constructor stub
	prop = new ConnectionProperty();
	}


 protected void doGet(HttpServletRequest request,
		 HttpServletResponse response)
		 throws ServletException, IOException {
		 response.setContentType("text/html");
		 String userPath;
		 List<Film> films;
		 FilmDbDAO dao = new FilmDbDAO();
		 try {
			 films = dao.findAll();
		 request.setAttribute("films", films);
		 } catch (Exception e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 userPath = request.getServletPath();
		
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/views/film.jsp");
			    dispatcher.include(request, response);
		 
		 }
 
 protected void doPost(HttpServletRequest request,
		 HttpServletResponse response)
		 
		 throws ServletException, IOException {
		 // TODO Auto-generated method stub
		 doGet(request, response);
		 }

}
