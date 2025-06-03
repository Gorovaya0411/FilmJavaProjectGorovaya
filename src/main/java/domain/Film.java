package domain;

public class Film {
	private Long id;
	 private String title;       // Название
	    private int releaseYear;    // Год выхода
	    private String director;    // Режиссер
	    private String genre;       // Жанр

	    // Конструкторы
	    public Film() {
	    }

	    public Film(Long id, String title, int releaseYear, String director, String genre) {
	        this.id = id;
	        this.title = title;
	        this.releaseYear = releaseYear;
	        this.director = director;
	        this.genre = genre;
	    }
	    
	    public Film(Long id) {
	        this.id = id;
	    }

	    // Геттеры и сеттеры
	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public int getReleaseYear() {
	        return releaseYear;
	    }

	    public void setReleaseYear(int releaseYear) {
	        this.releaseYear = releaseYear;
	    }

	    public String getDirector() {
	        return director;
	    }

	    public void setDirector(String director) {
	        this.director = director;
	    }

	    public String getGenre() {
	        return genre;
	    }

	    public void setGenre(String genre) {
	        this.genre = genre;
	    }

	    @Override
	    public String toString() {
	        return "Films {" + 
	               "Id = " + id + 
	               ", Title = " + title + 
	               ", ReleaseYear = " + releaseYear + 
	               ", Director = " + director + 
	               ", Genre = " + genre + "}";
	    }
	}