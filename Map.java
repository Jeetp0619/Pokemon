import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;

public class Map{
  private char [][] mp;
  private boolean [][] revealed;
  private static Map instance = null;

  /** Creates a default map 5 rows five collumns
  * also makes an accompanying boolean table
  */
  public Map(){
    mp = new char [5][5];
    revealed = new boolean [5][5];
  }

  public static Map getInstance() {
      if(instance == null) {
         instance = new Map();
      }
      return instance;
   }

  /** Passes in the integer representing the map
  * to be loaded
  * @param mapNum number of map to be loaded in
  */
  void loadMap(int mapNum){
    try{
      Scanner read = new Scanner(new File("Area"+mapNum+".txt"));
      while(read.hasNext()){
        for(int i = 0; i<mp.length;i++){
          String line = read.nextLine().replace(" ","");
          for(int j = 0; j<line.length();j++){
            mp[i][j]= line.charAt(j);
          }
        }
      }
      read.close();
    }catch(FileNotFoundException fnf){
      System.out.println("File was not found");
    }
  }

  /** Returns a character at a given point on the
  * map otherwise returns 0
  * @param Point p the point where the player is
  * @return char a point p otherwise return 0
  */
  public char getCharAtLoc(Point p){
    return mp[p.y][p.x];
  }
 
  /** Passes Point p where the player is and
  * displays the map
  * @param Point p  the point where the player is
  * @return s which displays the map
  */
  public String mapToString(Point p){
    String s = "";
    for(int i = 0; i<mp.length;i++){
      for(int j = 0; j<mp[0].length;j++){
        if(i == p.getY() && j == p.getX()){
          s +='*';
        }else if(!revealed[i][j]){
          s += "X";
        }else if(revealed[i][j] || mp[i][j] != 's'){
          s+= mp[i][j];
        }
        //includes a space after every character
        s+=" ";
      }
      //Starts a new line instead of printing all on one line
      s+="\n";
    }
    return s;
  }

  /** Finds the start 's' of the map
  * @return p which shows where the start of the
  * map is 
  */
  public Point findStart(){
    Point p = new Point();
    for(int i = 0; i < mp.length;i++){
      for(int j = 0; j<mp[0].length; j++){
        if(mp[i][j] == 's'){
          p.setLocation(j,i);
          revealed = new boolean [5][5];
          reveal(p);
        }
      }
    }
    return p;
  }

 /** Passes in Point p and reveals that tile
  * / point
  * @param Point p  the point to be revealed
  */

  void reveal(Point p){
   revealed[p.y][p.x] = true;    
  }

 /** Passes in Point p and removes that tile /point
  * @param Point p where the char is to be removed
  */
  void removeChartAtLoc(Point p){
    if(mp[p.y][p.x] != 's' && mp[p.y][p.x] != 'c'){
      mp[p.y][p.x] = 'n';
    }
  }
}