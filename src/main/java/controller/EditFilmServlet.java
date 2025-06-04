package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dataaccess.ConnectionProperty;
import dataaccess.FilmDbDAO;
import domain.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editfilm")
public class EditFilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public EditFilmServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        String userPath;
        List<Film> films;
        Film editFilm = null;
        FilmDbDAO dao = new FilmDbDAO();
        
        try {
            films = dao.findAll();
            request.setAttribute("films", films);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String strId = request.getParameter("id");
        Long id = null; // id редактируемого фильма
        if(strId != null) {
            id = Long.parseLong(strId);
        }
        
        try {
            editFilm = dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request.setAttribute("filmEdit", editFilm);
        userPath = request.getServletPath();
        
        if ("/editfilm".equals(userPath)) {
            request.getRequestDispatcher("/views/editfilm.jsp")
                   .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FilmDbDAO dao = new FilmDbDAO();
        
        String strId = request.getParameter("id");
        Long id = null;
        if(strId != null) {
            id = Long.parseLong(strId);
        }
        
        String title = request.getParameter("title");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        String director = request.getParameter("director");
        String genre = request.getParameter("genre");
        
        Film editFilm = new Film(id, title, releaseYear, director, genre);
        
        try {
            dao.update(editFilm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("/films/film");
    }
}