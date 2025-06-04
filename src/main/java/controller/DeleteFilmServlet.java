package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import dataaccess.ConnectionProperty;
import dataaccess.FilmDbDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet( "/deletefilm")
public class DeleteFilmServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
ConnectionProperty prop;

public DeleteFilmServlet() throws FileNotFoundException,
IOException {
 super();
 prop = new ConnectionProperty();
 }
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
FilmDbDAO dao = new FilmDbDAO();
String strId = request.getParameter("id");
Long deleteid = null;
if(strId != null) {
deleteid = Long.parseLong(strId);
}
try {
dao.delete(deleteid);
} catch (Exception e) {
e.printStackTrace();
}
response.sendRedirect("/films/film");
}

}
