package com.TomasSciarrillo.gamelibrary_api;
import jakarta.persistence.*;


@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String title;
    private String genre;
    private boolean played;
    private int rating;

    public Game(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.played = false;
        this.rating = 0;
    }
    
    public Game() {
    }

    public Long getId() {return id;}
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public boolean isPlayed() { return played; }
    public int getRating() { return rating; }

    public void setTitle(String title) {this.title=title;}
    public void setGenre(String genre) {this.genre=genre;}
    public void setPlayed(boolean played) { this.played = played; }
    public void setRating(int rating) { this.rating = rating; }
}