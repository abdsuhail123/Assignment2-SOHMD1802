public class mapTest{
    public static void main(String[] args){
        int rows = 9; 
        int col = 9;
        
        Map newMap = new Map(rows,col);
        newMap.addWalls();
        //newMap.setTile(1,7,A);
        System.out.println(newMap.toString());
    }
}
