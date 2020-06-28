import java.io.*; // Import Files and Exceptions
import java.util.Scanner; // Import the Scanner class

// This class reads in a .txt file
// The first line of the file contains row and col of the Map
// Then the map template is present which will be copied to our new map object

public class FileLoader
{
    //private static String mapFile = "mapFile.txt" ;
    private static int row ; // row variable
    private static int col ; // column variable

    public static Map loadMap(String fileName){
        char mapTemplate[][] ; //2D array of characters
        Map newMap = new Map(10,10); //Initiliazing our new map object

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

            //System.out.println(line);

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
                }
                line = buffer.readLine(); //reset to the next line
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
        return newMap;
    }
}
