package controller;

import domain.Character;
import domain.Film;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dataaccess.CharacterDbDAO;
import dataaccess.FilmDbDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CharacterServlet
 */
@WebServlet("/character")
public class CharacterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CharacterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String userPath;
        List<Character> characters;
        List<Film> films;
        
        FilmDbDAO daoFilm = new FilmDbDAO();
        CharacterDbDAO daoCharacter = new CharacterDbDAO();
        
        try {
            characters = daoCharacter.findAll();
            films = daoFilm.findAll();
            
            // Устанавливаем полные объекты Film для каждого персонажа
            for (Character character : characters) {
                Film film = findById(character.getMovie().getId(), films);
                character.setMovie(film);
            }
            
            request.setAttribute("characters", characters);
            request.setAttribute("films", films);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        userPath = request.getServletPath();
    
            request.getRequestDispatcher("/views/characters.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    /**
     * Метод для поиска фильма в коллекции по id
     * @param id - id фильма
     * @param films - коллекция фильмов
     * @return найденный Film или null
     */
    private Film findById(Long id, List<Film> films) {
        if (films != null) {
            for (Film film : films) {
                if (film.getId().equals(id)) {
                    return film;
                }
            }
        }
        return null;
    }
}