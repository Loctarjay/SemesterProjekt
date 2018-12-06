import java.util.*;
import java.text.*;

public class CompSwimmer extends Member{
   private int[] crawl;
   private int[] free;
   private int[] fly;
   private int[] crawlEvent;
   private int[] freeEvent;
   private int[] flyEvent;
   private boolean crawlComp;
   private boolean freeComp;
   private boolean flyComp;
   private boolean activityLevel;
   
   public CompSwimmer(String name, int age, String mail, boolean level, int arrears, boolean crawlComp, boolean freeComp, boolean flyComp) {
      super(name, age, mail, level, arrears);
      crawl = new int[5];
      crawl[0] = 99;
      crawl[1] = 99;
      free = new int[5];
      free[0] = 99;
      free[1] = 99;
      fly = new int[5];
      fly[0] = 99;
      fly[1] = 99;
      crawlEvent = new int[3];
      crawlEvent[0] = 99;
      crawlEvent[1] = 99;
      freeEvent = new int[3];
      freeEvent[0] = 99;
      freeEvent[1] = 99;
      flyEvent = new int[3];
      flyEvent[0] = 99;
      flyEvent[1] = 99;
      this.crawlComp = crawlComp;
      this.freeComp = freeComp;
      this.flyComp = flyComp;
   }
   
   
   // methods for comparing old time with new time (training)
   public void compareCrawlTime(int minute, int second, int day, int month, int year){
      if (crawl[0] >= minute){
         if (crawl[1] > second){
            crawl[0] = minute;
            crawl[1] = second;
            crawlDateUpdate(day, month, year);
            crawlComp = true;
         }
      }
   }
   public void compareFreeTime(int minute, int second, int day, int month, int year){
      if (free[0] >= minute){
         if (free[1] > second){
            free[0] = minute;
            free[1] = second;
            freeDateUpdate(day, month, year);
            freeComp = true;
         }
      }
   }
   public void compareFlyTime(int minute, int second, int day, int month, int year){
      if (fly[0] >= minute){
         if (fly[1] > second){
            fly[0] = minute;
            fly[1] = second;
            flyDateUpdate(day, month, year);
            flyComp = true;
         }
      }
   }
   
   
   
   
   
   // methods for setting new time (event)
   public void setCrawlEvent(int minute, int second, int place){
      crawlEvent[0] = minute;
      crawlEvent[1] = second;
      crawlEvent[2] = place;
   }
   public void setFreeEvent(int minute, int second, int place){
      freeEvent[0] = minute;
      freeEvent[1] = second;
      freeEvent[2] = place;
   }
   public void setFlyEvent(int minute, int second, int place){
      flyEvent[0] = minute;
      flyEvent[1] = second;
      flyEvent[2] = place;
   }
   
   
   
   
   
   // methods for updating the date
   public void crawlDateUpdate(int day, int month, int year){
      if (crawl[2] == year) {
         if (crawl[3] <= month) {
            if (crawl[4] < day) {
               //System.out.println("Old date is: " + crawl[2] + "-" + crawl[3] + "-" + crawl[4] + "\n");
               crawl[2] = day;
               crawl[3] = month;
               crawl[4] = year;
               //System.out.println("New date is: " + crawl[2] + "-" + crawl[3] + "-" + crawl[4] + "\n");
               
            }
         }
         
      } else if (crawl[2] < year) {
         crawl[3] = 0;
         if (crawl[3] <= month) {
            if (crawl[4] < day) {
               //System.out.println("Old date is: " + crawl[2] + "-" + crawl[3] + "-" + crawl[4] + "\n");
               crawl[2] = day;
               crawl[3] = month;
               crawl[4] = year;
               //System.out.println("New date is: " + crawl[2] + "-" + crawl[3] + "-" + crawl[4] + "\n");
               
            }
         }
         
      }
   }
   public void freeDateUpdate(int day, int month, int year){
      if (free[2] == year) {
         if (free[3] <= month) {
            if (free[4] < day) {
               //System.out.println("Old date is: " + free[2] + "-" + free[3] + "-" + free[4] + "\n");
               free[2] = day;
               free[3] = month;
               free[4] = year;
               //System.out.println("new date is: " + free[2] + "-" + free[3] + "-" + free[4] + "\n");
               
            }
         }
         
      } else if (free[2] < year) {
         free[3] = 0;
         if (free[3] <= month) {
            if (free[4] < day) {
               //System.out.println("Old date is: " + fly[2] + "-" + fly[3] + "-" + fly[4] + "\n");
               free[2] = day;
               free[3] = month;
               free[4] = year;
               //System.out.println("New date is: " + fly[2] + "-" + fly[3] + "-" + fly[4] + "\n");
               
            }
         }
         
      } else {
         System.out.println("not a possible year.");
      }
   }
   public void flyDateUpdate(int day, int month, int year){
      if (fly[2] == year) {
         if (fly[3] <= month) {
            if (fly[4] < day) {
               //System.out.println("Old date is: " + day + "-" + month + "-" + year + "\n");
               fly[2] = day;
               fly[3] = month;
               fly[4] = year;
               //System.out.println("New date is: " + day + "-" + month + "-" + year + "\n");
               
            }
         }
         
      } else if (fly[2] < year) {
         fly[3] = 0;
         if (fly[3] <= month) {
            if (fly[4] < day) {
               //System.out.println("Old date is: " + day + "-" + month + "-" + year + "\n");
               fly[2] = day;
               fly[3] = month;
               fly[4] = year;
               //System.out.println("New date is: " + day + "-" + month + "-" + year + "\n");
               
            }
         }
         
      } else {
         System.out.println("not a possible year.");
      }
   }
   
   
   
   
   
   
   
   
   
   // getters to see if they're in a competition.
   public boolean getCrawlComp(){
      return this.crawlComp;
   }
   public boolean getFreeComp(){
      return this.freeComp;
   }
   public boolean getFlyComp(){
      return this.flyComp;
   }
   
   // getters for the scores
   public void getCrawlScore(){
      System.out.println("For member: " + super.getName() + "\n" +
                         "Date: " + crawl[2] + "-" + crawl[3] + "-" + crawl[4] + "\n" +
                         "Time = " + crawl[0] + ":" + crawl[1] + "\n");
   }
   public void getFreeScore(){
      System.out.println("For member: " + super.getName() + "\n" +
                         "Date: " + free[2] + "-" + free[3] + "-" + free[4] + "\n" +
                         "Time = " + free[0] + ":" + free[1] + "\n");
   }
   public void getFlyScore(){
      System.out.println("For member: " + super.getName() + "\n" +
                         "Date: " + fly[2] + "-" + fly[3] + "-" + fly[4] + "\n" +
                         "Time = " + fly[0] + ":" + fly[1] + "\n");
   }
   
   
   
   
   
   // getters for the training arrays
   public int[] getCrawl(){
      return this.crawl;
   }
   public int[] getFree(){
      return this.free;
   }
   public int[] getFly(){
      return this.fly;
   }
   
   
   // getters for the event arrays
   public int[] getCrawlEvent(){
      return this.crawlEvent;
   }
   public int[] getFreeEvent(){
      return this.freeEvent;
   }
   public int[] getFlyEvent(){
      return this.flyEvent;
   }
}