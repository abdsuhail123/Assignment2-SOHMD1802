public class Encounter{

	// Encounter Stats
	private String statToCheck;
	private int difficulty; // difficulty of the stat check
	private Item reward;
	private int risk; // how much stress to gain if you lose. 
	
	// Encounter Messages to print.
	private String name = "Name";
	private String description = "Doesn't look like much of anything";
	private String winningDescription = " You win";
	private String losingDescription = "You lose!";

	private int id = -1; // no id until set. 
	// Encounter something at a place

	// Constructor accepting the skillType, difficulty, reward Item and risk. 
	// Note the descriptions are not initialized and you should use setDescriptions
	public Encounter(String stat, int diff, Item reward, int risk){
		statToCheck = stat;
		difficulty = diff;
		// Alternate example of setting variables. 
		this.reward = reward;
		this.risk = risk;
	}

	public void setDescriptions(String name, String basic, String win, String lose){
		this.name = name;
		description = basic;
		winningDescription = win;
		losingDescription = lose;
	}

	// Added A2
	public int getId(){
		return id;
	}
	// Added A2
	public void setId(int newId){
		id = newId;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getWinning(){
		return winningDescription;
	}

	public String getLosing(){
		return losingDescription;
	}

	public int getRisk(){
		return risk;
	}

	// Note I am removing the Item but also setting the original variable to null
	// to avoid duplicates. 
	public Item removeReward(){
		Item temp = reward;
		reward = null;
		return temp;
	}

	public String getStatToCheck(){
		return statToCheck;
	}

	public int getDifficulty(){
		return difficulty;
	}

	// Added for testing
	public String fullOutput(){
		String s = id + "\n";
		s += name + "\n";
		s += difficulty + " " + risk + "\n";
		s += reward.toString() + "\n";
		s += description + "\n";
		s += winningDescription + "\n";
		s += losingDescription + "\n";
		return s;
	}
}