package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import dataaccess.CharacterDbDAO;
import dataaccess.ConnectionProperty;
import dataaccess.FilmDbDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet( "/deletecharacter")
public class DeleteCharacterServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
ConnectionProperty prop;

public DeleteCharacterServlet() throws FileNotFoundException,
IOException {
 super();
 prop = new ConnectionProperty();
 }
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
CharacterDbDAO dao = new CharacterDbDAO();
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
response.sendRedirect("/films/character");
}

}
