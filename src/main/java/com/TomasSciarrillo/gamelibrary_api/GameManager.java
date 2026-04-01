package com.TomasSciarrillo.gamelibrary_api;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class GameManager {
	
	private List<Game> games;
	
	public GameManager() {
		games= new ArrayList<Game>();
	}
	
	public void addGame(Game game) {
		if(findGameByTitle(game.getTitle())!=null) {
			System.out.println("Game already exists.");
			return;
		}
		games.add(game);
		System.out.println("Game added.");
	}

	public List<Game> getGameList(){
		return games;
	}
	
	public void listGames() {
	    if (games.size() == 0) {
	        System.out.println("No games.");
	        return;
	    }

	    int i=1;
	    for (Game game:games) {
	    	System.out.println(
	    		    i + " - " +
	    		    game.getTitle() +
	    		    " (" + game.getGenre() + ")" +
	    		    " | Played: " + game.isPlayed() +
	    		    " | Rating: " + game.getRating()
	    		);
	    	i++;
	    }
	}
	
	
	public void removeGame(String title) {
		Game game= findGameByTitle(title);
		
		if (game==null) {
			System.out.println("Game not found.");
			return;
		}
	
		games.remove(game);
		System.out.println("Game removed.");
	}
	
	public void markAsPlayed(String title) {
		Game game= findGameByTitle(title);
		
		if (game==null) {
			System.out.println("Game not found.");
			return;
		}
		game.setPlayed(true);
		System.out.println("Game marked as played.");
	}
	
	public void rateGame (String title, int rating) {
		Game game = findGameByTitle(title);
		
		if (game==null) {
			System.out.println("Not game found.");
			return;
		}
		
		if (rating<1 || rating>5) {
			System.out.println("Rating must be between 1 and 5.");
			return;
		}
		game.setRating(rating);
		System.out.println("Game rated.");
	}
	
	public void listPlayedGames() {
		boolean found=false;
		
		for (Game game: games) {
			if (game.isPlayed()) {
				System.out.println(game.getTitle());
				found=true;
			}
		}
		if (!found) {
			System.out.println("No played games.");
		}
	}
	
	public void listUnplayedGames() {
		boolean found= false;
		
		for (Game game: games) {
			if (!game.isPlayed()) {
				System.out.println(game.getTitle());
				found=true;
			}
		}
		if (!found) {
			System.out.println("No unplayed games.");
		}
	}
	
	public void searchGame (String title) {

		Game game= findGameByTitle(title);
		
		if (game==null) {
			System.out.println("Not game found.");
			return;
		}
	    System.out.println(
	            game.getTitle() +
	            " | Genre: " + game.getGenre() +
	            " | Played: " + game.isPlayed() +
	            " | Rating: " + game.getRating()
	        );
	}
	
	public void saveToFile() {
		try (FileWriter writer = new FileWriter ("games.txt")){
			for (Game game: games) {
				writer.write(
					game.getTitle()+","+
					game.getGenre()+","+
					game.isPlayed()+","+
					game.getRating()+"\n"			
				);
			}
		}catch(IOException e) {
			System.out.println("Error saving file.");
		}
		}

	public void loadFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("games.txt"))) {
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				String parts[]= line.split(",");
				String title= parts[0];
				String genre= parts[1];
				boolean played= Boolean.parseBoolean(parts[2]);
				int rating= Integer.parseInt(parts[3]);
				
				Game game= new Game(title,genre);
				game.setPlayed(played);
				game.setRating(rating);
				
				games.add(game);
			}
		}catch(IOException e) {
			System.out.println("No previous data found.");
		}
	}

	private Game findGameByTitle(String title) {
		for (Game game: games) {
			if (game.getTitle().equalsIgnoreCase(title)) {
				return game;
			}
		}
		return null;
	}
}
	
