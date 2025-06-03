package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
* Servlet implementation class DirectorServlet_
*/
@WebServlet("/GenreServlet")
public class GenreServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

 /**
 * @see HttpServlet#HttpServlet()
 */
 public GenreServlet() {
 super();
 // TODO Auto-generated constructor stub
 }
/**
* @see HttpServlet#doGet(HttpServletRequest request,
HttpServletResponse response)
*/
 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
         throws IOException {
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     out.println("<html><body>");
     out.println("<form method='post'>");
     out.println("Введи любимый жанр: <input type='text' name='genre'><br>");
     out.println("<input type='submit' value='Отправить'>");
     out.println("</form>");
     out.println("</body></html>");
 }
/**
* @see HttpServlet#doPost(HttpServletRequest request,
HttpServletResponse response)
*/
 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws IOException {
	  
	    response.setContentType("text/html;charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    String genre = request.getParameter("genre");
	    response.getWriter().println("Привет, " + genre + " очень классный жанр!");
 }
}
