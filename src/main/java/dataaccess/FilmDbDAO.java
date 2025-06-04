package dataaccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataaccess.RepositoryDAO;
import domain.Film;
/**
* Класс реализации функций взаимодействия с базой данных для таблицы films
*/
public class FilmDbDAO implements RepositoryDAO<Film> {
    
    // SQL-запросы к таблице films
    private static final String SELECT_ALL_FILMS = 
        "SELECT id, title, release_year, director, genre FROM films ORDER BY title ASC";
    
    private static final String SELECT_FILM_BY_ID = 
        "SELECT id, title, release_year, director, genre FROM films WHERE id = ?";
    
    private static final String INSERT_FILM = 
        "INSERT INTO films(title, release_year, director, genre) VALUES(?, ?, ?, ?)";
    
    private static final String UPDATE_FILM = 
        "UPDATE films SET title = ?, release_year = ?, director = ?, genre = ? WHERE id = ?";
    
    private static final String DELETE_FILM = 
        "DELETE FROM films WHERE id = ?";
    
    private ConnectionBuilder builder = new DbConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Film film) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_FILM, new String[] { "id" })) {
            
            pst.setString(1, film.getTitle());
            pst.setInt(2, film.getReleaseYear());
            pst.setString(3, film.getDirector());
            pst.setString(4, film.getGenre());
            pst.executeUpdate();
            
            ResultSet gk = pst.getGeneratedKeys();
            Long id = -1L;
            if (gk.next()) {
                id = gk.getLong("id");
            }
            gk.close();
            return id;
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void update(Film film) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_FILM)) {
            
            pst.setString(1, film.getTitle());
            pst.setInt(2, film.getReleaseYear());
            pst.setString(3, film.getDirector());
            pst.setString(4, film.getGenre());
            pst.setLong(5, film.getId());
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_FILM)) {
            
            pst.setLong(1, id);
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Film findById(Long id) throws Exception {
        Film film = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_FILM_BY_ID)) {
            
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                film = fillFilm(rs);
            }
            rs.close();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return film;
    }

    @Override
    public List<Film> findAll() throws Exception {
        List<Film> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_FILMS);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                list.add(fillFilm(rs));
            }
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    private Film fillFilm(ResultSet rs) throws SQLException {
        Film film = new Film();
        film.setId(rs.getLong("id"));
        film.setTitle(rs.getString("title"));
        film.setReleaseYear(rs.getInt("release_year"));
        film.setDirector(rs.getString("director"));
        film.setGenre(rs.getString("genre"));
        return film;
    }

    public Film findById(Long id, List<Film> films) {
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
