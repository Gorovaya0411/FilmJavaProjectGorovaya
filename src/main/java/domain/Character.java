package domain;

public class Character {
	 private Long id;
    private String characterName;  // Имя персонажа
    private String actorName;     // Имя актера
    private String status;        // Статус
    // Навигационное свойство - ссылка на фильм
    private Film film;

    // Конструкторы
    public Character() {
    }

    public Character(Long id, String characterName, String actorName, String status, Film film) {
        this.id = id;
        this.characterName = characterName;
        this.actorName = actorName;
        this.status = status;
        this.film = film;
    }
    
    public Character(Long id) {
        this.id = id;
    }

    // Геттеры и сеттеры
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Film getMovie() {
        return film;
    }

    public void setMovie(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Character  {" + 
               "Id = " + id + 
               ", CharacterName = " + characterName + 
               ", ActorName = " + actorName + 
               ", Status = " + status + 
               ", Film = " + (film != null ? film.getTitle() : "null") + "}";
    }
}