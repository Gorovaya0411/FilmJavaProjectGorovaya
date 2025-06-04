package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import domain.Character;
import domain.Film;
import exception.DAOException;

/**
* Класс реализации функций взаимодействия с базой данных для таблицы characters
*/
public class CharacterDbDAO implements RepositoryDAO<Character> {

    // SQL-запросы к таблице characters
    private static final String SELECT_ALL_CHARACTERS = 
        "SELECT id, films_id, character_name, actor_name, status FROM characters ORDER BY character_name ASC";
    
    private static final String SELECT_CHARACTER_BY_ID = 
        "SELECT id, films_id, character_name, actor_name, status FROM characters WHERE id = ?";
    
    private static final String INSERT_CHARACTER = 
        "INSERT INTO characters(films_id, character_name, actor_name, status) VALUES(?, ?, ?, ?)";
    
    private static final String UPDATE_CHARACTER = 
        "UPDATE characters SET films_id = ?, character_name = ?, actor_name = ?, status = ? WHERE id = ?";
    
    private static final String DELETE_CHARACTER = 
        "DELETE FROM characters WHERE id = ?";
    
    private static final String SELECT_CHARACTERS_BY_MOVIE = 
        "SELECT id, films_id, character_name, actor_name, status FROM characters WHERE films_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Character character) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_CHARACTER, new String[] { "id" })) {
            System.out.println(character);
           // pst.setLong(1, character.getId());
            pst.setLong(1, 5L);
            pst.setString(2, character.getCharacterName());
            pst.setString(3, character.getActorName());
            pst.setString(4, character.getStatus());
            
            pst.executeUpdate();
            
         
            ResultSet gk = pst.getGeneratedKeys();
            Long id = -1L;
            if (gk.next()) {
                id = 5L;
            }
            gk.close();
            return id;
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void update(Character character) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_CHARACTER)) {
            
            pst.setLong(1, character.getMovie().getId());
            pst.setString(2, character.getCharacterName());
            pst.setString(3, character.getActorName());
            pst.setString(4, character.getStatus());
            pst.setLong(5, character.getId());
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_CHARACTER)) {
            
            pst.setLong(1, id);
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Character findById(Long id) throws Exception {
        Character character = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_CHARACTER_BY_ID)) {
            
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                character = fillCharacter(rs);
            }
            rs.close();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return character;
    }

    @Override
    public List<Character> findAll() throws Exception {
        List<Character> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_CHARACTERS);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                list.add(fillCharacter(rs));
            }
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    // Дополнительный метод для получения персонажей по фильму
    public List<Character> findByMovieId(Long movieId) throws Exception {
        List<Character> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_CHARACTERS_BY_MOVIE)) {
            
            pst.setLong(1, movieId);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                list.add(fillCharacter(rs));
            }
            rs.close();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    private Character fillCharacter(ResultSet rs) throws SQLException {
        Character character = new Character();
        character.setId(rs.getLong("id"));
        
        // Создаем временный объект Film только с id
        Film film = new Film();
        film.setId(rs.getLong("films_id"));
        character.setMovie(film);
        
        character.setCharacterName(rs.getString("character_name"));
        character.setActorName(rs.getString("actor_name"));
        character.setStatus(rs.getString("status"));
        return character;
    }

    public Character findById(Long id, List<Character> characters) {
        if (characters != null) {
            for (Character character : characters) {
                if (character.getId().equals(id)) {
                    return character;
                }
            }
        }
        return null;
    }
}
