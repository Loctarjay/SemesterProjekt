import java.io.*;
import java.util.*;

public class Files{
   
   // Competitive Files
   public void setCompFile(ArrayList<CompSwimmer> list, File file) throws FileNotFoundException {
      PrintStream toFile = new PrintStream(file);
      for (int count = 0; count < list.size(); count++){
         int[] crawlScore = list.get(count).getCrawl();
         int[] FreeScore = list.get(count).getFree();
         int[] FlyScore = list.get(count).getFly();
         toFile.println(list.get(count).getName() + "--" + list.get(count).getAge() + "--" + list.get(count).getMail() + "--" + list.get(count).getLevel() + "--" + list.get(count).getArrears() + "--" +
            list.get(count).getCrawlComp() + "--" + crawlScore[0] + "--" + crawlScore[1] + "--" + crawlScore[2] + "--" + crawlScore[3] + "--" + crawlScore[4] + "--" +
            list.get(count).getFreeComp() + "--" + FreeScore[0] + "--" + FreeScore[1] + "--" + FreeScore[2] + "--" + FreeScore[3] + "--" + FreeScore[4] + "--" +
            list.get(count).getFlyComp() + "--" + FlyScore[0] + "--" + FlyScore[1] + "--" + FlyScore[2] + "--" + FlyScore[3] + "--" + FlyScore[4]);
      }
   }
   public ArrayList<CompSwimmer> getCompFile(File file) throws FileNotFoundException {
      ArrayList<CompSwimmer> list = new ArrayList<CompSwimmer>();
      Scanner fromFile = new Scanner(file);
      String tempFile;
      while (fromFile.hasNextLine()){
         tempFile = fromFile.nextLine();
         String[] tempArray = tempFile.split("--");
         list.add(new CompSwimmer(tempArray[0], Integer.parseInt(tempArray[1]), tempArray[2], Boolean.parseBoolean(tempArray[3]), Integer.parseInt(tempArray[4]), Boolean.parseBoolean(tempArray[5]), Boolean.parseBoolean(tempArray[11]), Boolean.parseBoolean(tempArray[17])));
         for (int count = 0; count <list.size(); count++){
            if (list.get(count).getName().equalsIgnoreCase(tempArray[0])){
               // Crawl
               list.get(count).compareCrawlTime(Integer.parseInt(tempArray[6]), Integer.parseInt(tempArray[7]), Integer.parseInt(tempArray[8]), Integer.parseInt(tempArray[9]), Integer.parseInt(tempArray[10]));
               // Free
               list.get(count).compareFreeTime(Integer.parseInt(tempArray[12]), Integer.parseInt(tempArray[13]), Integer.parseInt(tempArray[14]), Integer.parseInt(tempArray[15]), Integer.parseInt(tempArray[16]));
               // Fly
               list.get(count).compareFlyTime(Integer.parseInt(tempArray[18]), Integer.parseInt(tempArray[19]), Integer.parseInt(tempArray[20]), Integer.parseInt(tempArray[21]), Integer.parseInt(tempArray[22]));
            }
         }
      }
      return list;
   }
   
   
   
   // Casual files
   public void setCasualFile(ArrayList<Member> list, File file) throws FileNotFoundException {
      PrintStream toFile = new PrintStream(file);
      for (int count = 0; count < list.size(); count++){
         toFile.println(list.get(count).getName() + "--" + list.get(count).getAge() + "--" + list.get(count).getMail() + "--" + list.get(count).getLevel() + "--" + list.get(count).getArrears());
      }
   }
   
   public ArrayList<Member> getCasualFile(File file) throws FileNotFoundException {
      ArrayList<Member> list = new ArrayList<Member>();
      Scanner fromFile = new Scanner(file);
      String tempFile;
      while (fromFile.hasNextLine()){
         tempFile = fromFile.nextLine();
         String[] tempArray = tempFile.split("--");
         list.add(new Member(tempArray[0], Integer.parseInt(tempArray[1]), tempArray[2], Boolean.parseBoolean(tempArray[3]), Integer.parseInt(tempArray[4])));
      }
      return list;
   }
   
   
   
   // Event files
   public void setEventFile(CompSwimmer[] crawl, CompSwimmer[] free, CompSwimmer[] fly, File file) throws FileNotFoundException {
      // existing event file
      if (file.exists()){
      Scanner scan = new Scanner(file);
      String lines = "";
      while(scan.hasNextLine()){
         lines += scan.nextLine() + "\n";
      }
      if (crawl[0] != null){
         for (int count = 0; count < 1; count++){
            int[] crawlScore = crawl[count].getCrawlEvent();
            lines += "Crawl score for " + crawl[count].getName() + " is: " + crawlScore[0] + ":" + crawlScore[1] + ", at place: " + crawlScore[2];
         }
      }
      if (free[0] != null){
         for (int count = 0; count < 1; count++){
            int[] FreeScore = free[count].getFreeEvent();
            lines += "Free score for " + free[count].getName() + " is: " + FreeScore[0] + ":" + FreeScore[1] + ", at place: " + FreeScore[2];
         }
      }
      if (fly[0] != null){
         for (int count = 0; count < 1; count++){
            int[] FlyScore = fly[count].getFlyEvent();
            lines += "Fly score for " + fly[count].getName() + " is: " + FlyScore[0] + ":" + FlyScore[1] + ", at place: " + FlyScore[2];
         }
      }
      PrintStream toFile = new PrintStream(file);
      toFile.print(lines);
      
      //new event file
      } else {
      String lines = "";
      if (crawl[0] != null){
         for (int count = 0; count < 1; count++){
            int[] crawlScore = crawl[count].getCrawlEvent();
            lines += "Crawl score for " + crawl[count].getName() + " is: " + crawlScore[0] + ":" + crawlScore[1] + ", at place: " + crawlScore[2] + "\n";
         }
      }
      if (free[0] != null){
         for (int count = 0; count < 1; count++){
            int[] FreeScore = free[count].getFreeEvent();
            lines += "Free score for " + free[count].getName() + " is: " + FreeScore[0] + ":" + FreeScore[1] + ", at place: " + FreeScore[2] + "\n";
         }
      }
      if (fly[0] != null){
         for (int count = 0; count < 1; count++){
            int[] FlyScore = fly[count].getFlyEvent();
            lines += "Fly score for " + fly[count].getName() + " is: " + FlyScore[0] + ":" + FlyScore[1] + ", at place: " + FlyScore[2] + "\n";
         }
      }
      PrintStream toFile = new PrintStream(file);
      toFile.print(lines);
      }
      
   }
   public void getEventFile(File file) throws FileNotFoundException{
      Scanner scan = new Scanner(file);
      while(scan.hasNextLine()){
         System.out.println(scan.nextLine());
      }
   }
   
}