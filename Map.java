// A data class for storing a map of an Area. 
// This will handle the 2d array of art types for us. 
public class Map{

    public static boolean DEBUG_MODE = false;

    private char[][] currentMap; // encoded as char
    // Added later to allow the hero to be stored in the map. 
    private int heroRow = 0;
    private int heroCol = 0;

    // Later
    //private Tile[][] tiles; 
    //   private Encounter[][] encounters; // may have null entries. 

    // - Art Stuff - 
    // later phases. 
    // in pixels
    /*
    private int mapSize = 512; // in pixels
    private int tileSize = 64; // 
    private int tilesPerSide = 8;
     */

    // Create a new blank map with all fields set to '.'
    public Map(int rows, int cols){
        currentMap = new char[rows][cols];

        for(int r = 0; r < rows; r++){
            for(int c =0 ; c < cols; c++){
                currentMap[r][c] = '.';
            }
        }
    }

    // Generate the map based on the template
    // Added some code myself to suit 
    public Map(char[][] mapTemplate, int rows1, int cols1){
        currentMap = new char[rows1][cols1];

        for(int r = 0; r < rows1; r++){
            for(int c =0 ; c < cols1; c++){
                currentMap[r][c] = mapTemplate[r][c];
            }
        }
        heroRow = rows1; heroCol = cols1;
    }

    // Set every tile on the edge of the map (top/bottom and sides) to = 'X'
    // For full marks you should NOT iterate through every single tile in the map 
    public void addWalls(){
        for(int r = 0; r < currentMap.length; r++){
            currentMap[r][0] = 'X';
            currentMap[r][currentMap[r].length-1] = 'X';
        }
        for(int c = 1; c < currentMap[0].length-1; c++){
            currentMap[0][c] = 'X';
            currentMap[currentMap.length-1][c] = 'X';
        }
    }

    // Set the given tile. You should first check that the currentMap is not null
    // and that the row and col are valid entries in the array (not out of bounds)
    // If map exists and row/col are valid, set the given entry to value and return true
    // otherwise make no changes, print out a warning to the console and return false. 
    public boolean setTile(int row, int col, char value){
        if(currentMap == null){
            System.out.println("Warning, no map was found (map is null)");
            return false;
        }

        if( row < 0 || row >= currentMap.length || col < 0 || col >= currentMap[0].length){
            System.out.println("Invalid row or column given: [" + row +"," + col + "] map is " + currentMap.length + "x" + currentMap[0].length);
            return false;
        }

        currentMap[row][col] = value;

        return true;
    }

    // Map Art Stuff. 
    // Print out the 2d array as Chars, use '\n' to put each row on a new line
    public String toString(){
        String retStr = "";
        for(int row = 0; row <currentMap.length; row++ ){
            for(int col = 0; col < currentMap[row].length; col++){

                if( heroRow == row && heroCol == col){
                    retStr += 'H';
                }else{
                    retStr += currentMap[row][col];
                }

            }
            retStr += '\n';
        }
        return retStr;
    }

    // Updates the Map variables keeping track of the hero position. 
    // This is required since the hero location is just an H in an array
    // so we convert it to coordinates to keep it simpler. 
    public void initHeroLocation(){
        for(int i = 0; i < currentMap.length; i++ ){
            for(int j = 0; j < currentMap[i].length;j++){
                if(currentMap[i][j] == 'H'){
                    // set the hero position
                    heroRow = i;
                    heroCol = j;
                    return; // quit the loop. This is sort of hacky technique. 
                }
            }
        }
    }

    // Movement
    // Accept the map reference and a direction.
    // 0 = up, 1 = right, 2 = down, 3 = left 
    // return the char of the tile we moved onto 
    //z(if it is valid it should be a number)	
    public char processInput(int direction){

        int newMoveRow = heroRow;
        int newMoveCol = heroCol;
        // Move up if we can. 
        if(direction == 0){
            newMoveRow--;
        }else if(direction == 1){
            newMoveCol++;
        }else if(direction == 2){
            newMoveRow++;
        }else if(direction == 3){
            newMoveCol--;
        }

        char code = '.';

        if( isValidMove(newMoveRow, newMoveCol) ){
            code = applyMove(heroRow, heroCol, newMoveRow, newMoveCol);
        }

        return code;

    }

    // checks if the tile has anything on it. In this case it is open if it is not 'X'
    private boolean isValidMove(int row, int col){
        if(currentMap[row][col] != 'X'){
            return true;			
        }else{
            return false;
        }
    }

    // This moves the Hero 'H' from a given tile to another. 
    // Called by processInput
    // Apply the hero move, update the row and col of the Hero. 
    // returns the char that is at the target position (moved onto)
    // This is useful for identifying when we move onto an Encounter. 
    private char applyMove(int startRow, int startCol, int endRow, int endCol){
        // move H from startRow/startCol to endRow/endCol
        char returnVal = currentMap[endRow][endCol];

        currentMap[startRow][startCol] = '.';
        currentMap[endRow][endCol] = 'H';

        // Cache for later, optional. 
        heroRow = endRow;
        heroCol = endCol;

        return returnVal;
    }
   

    /*
    // [ ] Last Phase, add Art
    // Take each char in the array and draw the corresponding image. 
    // laying them out in a grid might be tricky, you can keep a fixed size for now
    // of 64 px images on a 512 px map
    public void drawMap(){
    for(int row =0; row < currentMap.length; row++){
    for(int col = 0; col < currentMap[row].length; col++){
    //	if((row + col)%2 == 0)
    double xOffset = col * tileSize + (tileSize/2.0);
    xOffset = xOffset / 512.0;
    double yOffset = row * tileSize + (tileSize)/2.0;
    yOffset = yOffset / 512.0;

    // [ ] Load the appropriate art. 
    String loadFile = lookUpArt(currentMap[row][col]);
    StdDraw.picture(xOffset, yOffset, loadFile);
    }
    }
    }

    // convert the char to the string filename. 
    private String lookUpArt(char tileCode){

    String returnVal=null;

    if(tileCode == 'X'){
    returnVal = "groundRock.png";
    }else if(tileCode == '.'){
    returnVal = "ground.png";
    }else{
    // nothing found. 
    System.out.println("Art not found for char: " + tileCode);
    returnVal = "Person0.png";
    }

    return returnVal;
    }

    public static void main(String[] args){
    Map m = new Map(5,6);
    System.out.println(m.toString());

    m = new Map(7,3);
    m.addWalls();
    System.out.println(m.toString());	

    m = new Map(3,7);
    m.addWalls();
    System.out.println(m.toString());		

    m = new Map(10,15);
    m.addWalls();
    m.setTile(1,1,'H');
    m.setTile(3,5,'1');
    System.out.println(m.toString());
    }
     */

    /* Sample Output: 
    Empty map 5 rows x 6 cols

    ......
    ......
    ......
    ......
    ......

    // 7R x 3C with walls
    XXX
    X.X
    X.X
    X.X
    X.X
    X.X
    XXX

    // 3R x 7Col with walls
    XXXXXXX
    X.....X
    XXXXXXX

    // Example with hero and encounters (0, 1, 2)
    XXXXXXXXXXXXXXX
    XH........0...X
    X.............X
    X....1........X
    X.............X
    X.............X
    X.......2.....X
    X.............X
    X.............X
    XXXXXXXXXXXXXXX
     */


}