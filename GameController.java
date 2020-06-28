import java.awt.event.KeyEvent;

/*
 *         GameController Class
 *      Runs all the other scripts
 *   
 */

public class GameController{
    private static int numOfEvents;
    public static void main(String[] args){
        
        // New Hero Loaded from .txt file
        Hero mainHero = FileLoader.loadHero("heroFile.txt");
        
        // New Map Loaded from .txt file
        Map mainMap = FileLoader.loadMap("mapFile.txt");
        
        // This ToString method prints out all the information
        // of the hero in one full string
        System.out.println(mainHero.fullToString()+'\n');

        //======================================================

        //The following for loop creates an array of Encounter
        //objects and fills it with randomly generatated Encounter objects
        
        numOfEvents = RandomGenerator.randomRoll(3,11);
        Encounter[] eventList = new Encounter[numOfEvents];
        for(int i = 0; i < numOfEvents; i++){
            // Assigning random Stat to encounter
            String statToCheck = RandomGenerator.getStatToCheck(); 
            // Generating Random Encounter Difficulty
            int difficulty = RandomGenerator.randomRoll(1,11);
            // Generating Random risk value for encounter
            int risk = RandomGenerator.randomRoll(1,6);
            // Creating new Random Item for Reward
            Item reward = new Item();

            //Creating Encounter
            Encounter event = new Encounter(statToCheck,difficulty,reward,risk);
            //Filling array with Encounters
            eventList[i] = event;
        }
        int counter = 0 ;//To keep count of events

        //While loop calls nextEncounter on each Encounter in the array
        while(counter<numOfEvents){
            nextEncounter(mainHero,eventList[counter]);
            counter++;
        }

        //Final Output Print
        System.out.println('\n'+"=== Final Output ===");
        System.out.println(mainHero.fullToString());
        System.out.println("==================");
        System.out.println("Thank you for playing. The End");
    }

    private static void nextEncounter(Hero hero, Encounter event){
        // Everytime this method is called
        // First a roll integer is randomly created
        // This integer value adds to the Hero Skill being tested
        // and then checked against the Encounter difficulty.
        // If Skill value is higher than Encounter difficulty,
        // the Hero wins the Encounter and Vice Versa
        int roll = RandomGenerator.randomRoll(1,4);
        boolean winStatus = false; // becomes true if Hero wins

        // Print out encounter details
        System.out.println("==================" + '\n');
        System.out.println("Your new enemy is the " + event.getName() + " " + event.getDescription());

        // Checking if Stat of Encounter matches Hero's Physical Skill
        // If it does, compare both values and print out result
        if(event.getStatToCheck()=="physical"){
            if(hero.getPhysical()+roll>=event.getDifficulty()){
                winStatus = true;
            }
        }

        // Checking if Stat of Encounter matches Hero's Mental Skill
        // If it does, compare both values and print out result
        if(event.getStatToCheck()=="mental"){
            if(hero.getMental()+roll>=event.getDifficulty()){
                winStatus = true;
            }
        }

        // Checking if Stat of Encounter matches Hero's Social Skill
        // If it does, compare both values and print out result
        if(event.getStatToCheck()=="social"){
            if(hero.getSocial()+roll>=event.getDifficulty()){
                winStatus = true;
            }
        }

        // Print message if Hero won Encounter
        if(winStatus == true){
            Item newItem = new Item();
            hero.addItem(newItem);
            System.out.println(event.getWinningDescription() +'\n'+"You get the reward: " + newItem);
        }
        // Print message if Hero lost Encounter
        else{
            int stress = RandomGenerator.randomRoll(1,4);
            hero.addStress(stress);
            System.out.println(event.getLosingDescription());
            System.out.println("Hero gains stress level " + stress);
        }

        System.out.println("==================");
    }
    
    // This method recognizes when a Key is press on the keyboard by the user
    // Each key press returns one integer value which will later be used
    // to move our Hero in the specific direction
    
    public static int getInput(){
        int counter = -1 ; // Default -1 (No key press)
        
        // 0 if Up Key Pressed
        if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            counter = 0 ;
        }

        // 1 if Right Key Pressed
        if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            counter = 1 ;
        }

        // 2 if Down Key Pressed
        if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            counter = 2 ;
        }

        // 3 if Left Key Pressed
        if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            counter = 3 ;
        } 
        return counter; // Return value of pressed key
    }
}