/*
 * Game class that Contains game information
 */
public class Games {
	private int gameID;
	private String gameName;
	private String gameDescription;
	private String gameLocation;
	
	/*
	 * Main Game constructor stores array info
	 */
	
	public Games(int gameID, String gameName, String gameDescription, String gameLocation) {
		setGameID(gameID);
		setGameName(gameName);
		setGameDescription(gameDescription);
		setGameLocation(gameLocation);
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public String getGameLocation() {
		return gameLocation;
	}

	public void setGameLocation(String gameLocation) {
		this.gameLocation = gameLocation;
	}
	
	public void LaunchGame(String gameLocation) {
		//TODO: Create or reference launch method
	}
	
} //end of Game class
