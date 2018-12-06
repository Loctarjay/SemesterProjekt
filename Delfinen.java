import java.io.*;
import java.text.*;

public class Delfinen{
   
   public static void main(String[] args) throws FileNotFoundException{
      File compFile = new File("CompList.txt");
      File casualFile = new File("CasualList.txt");
      File eventFile = new File("EventFile.txt");
      Rules rule = new Rules(compFile, casualFile, eventFile);
      
      
      rule.menu();
      
   }
   
}