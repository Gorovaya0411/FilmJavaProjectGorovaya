package controller;


import domain.Character;
import domain.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dataaccess.CharacterDbDAO;
import dataaccess.FilmDbDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/editcharacter")
public class EditCharacterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCharacterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        String userPath;
        List<Character> characters;
        List<Film> films;
        Character editcharacter = null;
        
        CharacterDbDAO daoCharacter = new CharacterDbDAO();
        FilmDbDAO daoFilm = new FilmDbDAO();
        
        try {
            characters = daoCharacter.findAll();
            films = daoFilm.findAll();
            request.setAttribute("characters", characters);
            request.setAttribute("films", films);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String strId = request.getParameter("id");
        Long id = null; // id редактируемого персонажа
        if(strId != null) {
            id = Long.parseLong(strId);
        }
        
        try {
            editcharacter = daoCharacter.findById(id);
            if (editcharacter != null) {
                // Устанавливаем полный объект Film для персонажа
                Film film = daoFilm.findById(editcharacter.getMovie().getId());
                editcharacter.setMovie(film);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request.setAttribute("characterEdit", editcharacter);
        userPath = request.getServletPath();
        
        if ("/editcharacter".equals(userPath)) {
            request.getRequestDispatcher("/views/editcharacter.jsp")
                   .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        CharacterDbDAO daoCharacter = new CharacterDbDAO();
        FilmDbDAO daoFilm = new FilmDbDAO();
        
        String strId = request.getParameter("id");
        Long id = null;
        if(strId != null) {
            id = Long.parseLong(strId);
        }
        
        String characterName = request.getParameter("characterName");
        String actorName = request.getParameter("actorName");
        String status = request.getParameter("status");
        String filmIdStr = request.getParameter("filmId");
        Long filmId = Long.parseLong(filmIdStr);
        
        try {
            Film film = daoFilm.findById(filmId);
            Character editcharacter = new Character(id, characterName, actorName, status, film);
            daoCharacter.update(editcharacter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("/films/character");
    }
}
