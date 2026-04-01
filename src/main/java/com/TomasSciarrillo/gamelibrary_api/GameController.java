package com.TomasSciarrillo.gamelibrary_api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.server.ResponseStatusException;
//import org.springframework.http.HttpStatus;

@RestController
public class GameController {

	private final GameRepository repository;

	public GameController(GameRepository repository) {
	    this.repository = repository;
	}

	/*
    public GameController() {
        manager.addGame(new Game("FIFA", "Sports"));
        manager.addGame(new Game("COD", "Shooter"));
    }
    */

	@GetMapping("/")
	public String home() {
		return "Game Library API is running.";
	}
	
    @GetMapping("/games")
    public List<Game> getGames() {
    	return repository.findAll();
    }
    
    @PostMapping("/games")
    public void addGame(@RequestBody Game game) {
    	repository.save(game);
    }

    
    @DeleteMapping("/games/{id}")
    public String deleteGame(@PathVariable Long id) {
    	
    	if (!repository.existsById(id)) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
    	}
    	repository.deleteById(id);
    	
    	return "Game deleted succesfully.";
    }
    
    
    @PutMapping("/games/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game updatedGame) {
    	
    	Game game = repository.findById(id)
    			.orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Game not found"));
    	
    	game.setTitle(updatedGame.getTitle());
    	game.setGenre(updatedGame.getGenre());
    	game.setPlayed(updatedGame.isPlayed());
    	game.setRating(updatedGame.getRating());
    	
    	return repository.save(game);
    }
    
    /*
    @PutMapping("/games/{title}/rate")
    public void rateGame(@PathVariable String title, @RequestParam int rating) {
    	manager.rateGame(title, rating);
    }
    */
}