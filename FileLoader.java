import java.io.*; // Import Files and Exceptions
import java.util.Scanner; // Import the Scanner class

/* The FileLoader class reads in data from .txt files for our code */     

public class FileLoader
{
    //=========================== For loadHero Method ============================

    private static Hero newHero = new Hero(); //New Hero Object
    private static String name; // Variable for Hero Name
    private static String description; // Variable for Hero Description
    private static int stress; // Variable for Hero Stress
    private static int maxStress; // Variable for Maximum Stress
    private static int physical; // Variable for Physical Skill
    private static int mental; // Variable for Mental Skill
    private static int social; // Variable for Social Skill
    private static Item newItem ; // Variable for new Item

    //=========================== For loadMap  Method ============================

    private static char mapTemplate[][] ; //2D array of characters
    private static Map newMap = new Map(10,10); //Initiliazing our new map object
    private static int row ; // row variable
    private static int col ; // column variable
    //private static String mapFile = "mapFile.txt" ;

    //=============================================================================

    /* loadMap Method */

    // This method reads in a "mapFile.txt"
    // The first line of the file contains row and col of the Map
    // Then the map template is present which will be copied to our new map object

    public static Map loadMap(String fileName){
        // Try catch
        try{
            // Load the file without errors
            FileReader reader = new FileReader(fileName);

            // BufferedReader will read in our file
            BufferedReader buffer = new BufferedReader(reader);

            // Get the next line from the BufferedReader
            String line = buffer.readLine();

            //If there is no first line
            if(line==null){
                //Error message
                System.out.println("Error, No data in saved text file");
            }

            // Scanner parses the line
            Scanner scan = new Scanner(line);

            row = scan.nextInt(); // first int in text file is row no.
            col = scan.nextInt(); // second int in text file is column no.
            //line = buffer.readLine();

            // 2D Array according to dimensions in text file
            mapTemplate = new char[row][col]; 
            char index ; // variable for characters
            int token ; // token variable (int values)

            // Filling our 2D array with map template in the text file
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    token = buffer.read(); //Reading in token value as int
                    index = (char)token ; //casting token value as char in index
                    mapTemplate[i][j] = index; //placing that index
                    //System.out.print(mapTemplate[i][j]);
                }
                line = buffer.readLine(); //reset to the next line
                //System.out.println();
            }

            //Creating a new Map with our template
            newMap = new Map(mapTemplate,row,col);

            //Closing the file operations
            reader.close();

        }catch(IOException exc){ // Catch block for IOException
            //Error message
            System.out.println("Error reading in file/ IO Exception");
            //Error Trace
            exc.printStackTrace();

        }catch(Exception exc){ // Catch block for random exception
            //Error message
            System.out.println("Unknown Exception");
            //Error Trace
            exc.printStackTrace();
        }
        return newMap; // Return the newMap
    }

    /* loadHero Method */

    // This method reads in the "heroFile.txt"
    // The first line has the name, the second line has the description
    // The third line has the Integer values for stress and maxstress
    // The fourth line has the Integer values for skills (Physical,Mental,Social)
    // The fifth line has ONE item in the Hero inventory

    public static Hero loadHero(String heroFile){

        // Try Catch
        try{
            // Load the file without errors
            FileReader reader = new FileReader(heroFile);

            // BufferedReader will read in our file
            BufferedReader buffer = new BufferedReader(reader);

            // Get the 1st line from the BufferedReader
            String line = buffer.readLine();
            //If there is no first line
            if(line==null){
                //Error message
                System.out.println("Error, No data in saved text file");
            }
            name = line; // 1st line is the name in the format

            // Get the 2nd line from the BufferedReader
            line = buffer.readLine();
            description = line ; // 2nd line is the description in the format

            // Get the 3rd line from the BufferedReader
            line = buffer.readLine(); 

            // New Scanner to parse the lines
            Scanner scan = new Scanner(line);

            stress = scan.nextInt(); // 1st integer in 3rd line = stress
            maxStress = scan.nextInt(); // 2nd integer in 3rd line = maxStress

            // Get the 4th line from the BufferedReader
            line = buffer.readLine();

            // Using scanner again
            scan = new Scanner(line);

            physical = scan.nextInt(); // 1st integer in 4th line = physicalSkill
            mental = scan.nextInt(); // 2nd integer in 4th line = mentalSkill
            social = scan.nextInt(); // 3rd integer in 4th line = socialSkill

            // Getting the fifth line (Contains ONE random item)
            line = buffer.readLine();
            newItem = new Item(line); // Reading name of item
            newHero.addItem(newItem); // Adding item to Hero inventory

            // Creating new Hero object.
            // Name, Description, Stress, MaxStress, Physical, Mental and Social
            // All are read from the .txt file

            newHero = new Hero(name,description,stress,maxStress);
            newHero.setPhysical(physical);
            newHero.setMental(mental);
            newHero.setSocial(social);

            //System.out.println(newHero.fullToString());

        }catch(IOException exc){ // Catch block for IOException
            //Error message
            System.out.println("Error reading in file/ IO Exception");
            //Error Trace
            exc.printStackTrace();

        }catch(Exception exc){ // Catch block for random exception
            //Error message
            System.out.println("Unknown Exception");
            //Error Trace
            exc.printStackTrace();
        }
        return newHero; // Return the newHero
    }
}
