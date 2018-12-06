import java.util.*;
import java.io.*;

public class Rules{
   private ArrayList<CompSwimmer> compList;
   private ArrayList<Member> casualList;
   private Files file;
   private File compFile;
   private File casualFile;
   private File eventFile;
   private CompSwimmer[] crawlComp;
   private CompSwimmer[] freeComp;
   private CompSwimmer[] flyComp;
   
   public Rules(File compFile, File casualFile, File eventFile) throws FileNotFoundException {
      this.compList = new ArrayList<CompSwimmer>();
      this.casualList = new ArrayList<Member>();
      file = new Files();
      this.compFile = compFile;
      this.casualFile = casualFile;
      this.eventFile = eventFile;
      crawlComp = new CompSwimmer[1];
      freeComp = new CompSwimmer[1];
      flyComp = new CompSwimmer[1];
      if (this.compFile.exists()){
         System.out.println("Competitive file exists");
         compFromFile(file.getCompFile(compFile));
      } else {
         System.out.println("No competitive file");
      }
      if (this.casualFile.exists()){
         System.out.println("Casual file exists");
         casualFromFile(file.getCasualFile(casualFile));
      } else {
         System.out.println("No casual file");
      }
   }
   
   public void menu() throws FileNotFoundException {
      boolean run = true;
      do{
         System.out.println("Press 1: Add a member.\n" +
                            "Press 2: Cashier menu.\n" +
                            "Press 3: Coach menu.\n" + 
                            "Press 4: See list of casual members.\n" +
                            "Press 5: See list of competitive members.\n" +
                            "Press 9: Exit program.\n");
         Scanner scan = new Scanner(System.in);
         int choice = scan.nextInt();
         
         
         // Menu number 1: add member
         if (choice == 1){
            int memberType = 0;
            do{
               System.out.println("Press 1: Casual swimmer.\n" + 
                                  "Press 2: Competitive swimmer.\n" +
                                  "Press 9: Exit add member.");
               Scanner memberScan = new Scanner(System.in);
               memberType = memberScan.nextInt();
               
               // add casual member
               if (memberType == 1){
                  Scanner addScan = new Scanner(System.in);
                  System.out.println("Enter name?");
                  String name = addScan.nextLine();
                  System.out.println("Enter age?");
                  int age = addScan.nextInt();
                  // to consume the rest of the line after using nextInt
                  addScan.nextLine();
                  // onwards with the member information
                  System.out.println("Enter E-mail.");
                  String mail = addScan.nextLine();
                  boolean levelType = false;
                  boolean runLevel = true;
                  String level;
                  do{
                     System.out.println("Enter activity level: Active / Passive");
                     level = addScan.nextLine();
                     if (level.equalsIgnoreCase("active")){
                        levelType = true;
                        runLevel = false;
                     } else if (level.equalsIgnoreCase("passive")){
                        levelType = false;
                        runLevel = false;
                     }
                  } while (runLevel);
                  casualList.add(new Member(name, age, mail, levelType, 0));
               
               
               // add competitive member
               } else if (memberType == 2){
                  Scanner addScan = new Scanner(System.in);
                  System.out.println("Enter name?");
                  String name = addScan.nextLine();
                  System.out.println("Enter age?");
                  int age = addScan.nextInt();
                  // to consume the rest of the line after using nextInt
                  addScan.nextLine();
                  // onwards with the member information
                  System.out.println("Enter E-mail.");
                  String mail = addScan.nextLine();
                  boolean levelType = false;
                  boolean runLevel = true;
                  String level;
                  do{
                     System.out.println("Enter activity level: Active / Passive");
                     level = addScan.nextLine();
                     if (level.equalsIgnoreCase("active")){
                        levelType = true;
                        runLevel = false;
                     } else if (level.equalsIgnoreCase("passive")){
                        levelType = false;
                        runLevel = false;
                     }
                  } while (runLevel);
                  compList.add(new CompSwimmer(name, age, mail, levelType, 0, false, false, false));
               } else if (memberType == 9){
                  System.out.println();
               } else {
                  System.out.println("Wrong input.");
               }
            } while (memberType != 9);
            
            
         // Menu number 2: cashier menu
         } else if (choice == 2) {
            cashierMenu();
               
         // Menu number 3: Coach menu
         } else if (choice == 3){
            boolean runCoach = true;
            do{
               runCoach = coachMenu();
            } while (runCoach);
            
         // Menu number 4: See list of casual members
         } else if (choice == 4){
            seeAllCasual();
         
         // Menu number 5: See list of competitive members
         } else if (choice == 5){
            seeAllComp();
         
         
         // Exit program
         } else if (choice == 9){
            run = false;
         } else {
            System.out.println("Wrong input");
         }         
         
         // save to files every time menu loads
         file.setCompFile(compToFile(), compFile);
         file.setCasualFile(casualToFile(), casualFile);
      }while (run);
   }
   
   
   
   
   
   
   // Coach Menu
   public boolean coachMenu() throws FileNotFoundException {
      boolean runCoach = true;
      System.out.println("Press 1: See training score.\n" +
                         "Press 2: See competitive score.\n" +
                         "Press 3: Select competitor.\n" +
                         "Press 4: Update score.\n" +
                         "Press 9: Exit coach menu.\n");
      Scanner scan = new Scanner(System.in);
      int choice = scan.nextInt();
      // see training score
      if (choice == 1){
         seeTrainingScore();
      
      // see competitive score
      } else if (choice == 2){
         if (this.eventFile.exists()){
            file.getEventFile(eventFile);
         } else {
            System.out.println("No events recorded, come back another time.");
         }
      // select competitor
      } else if (choice == 3){
         boolean selection = true;
         do{
            System.out.println("Which competition do you want to select competitors for?");
            System.out.println("Press 1: Crawl competitors.\n" +
                               "Press 2: Free competitors.\n" +
                               "Press 3: Fly competitors.\n" +
                               "Press 9: Exit selection menu.");
            int selectionChoice = scan.nextInt();
            scan.nextLine();
            
            // Crawl competitor
            if (selectionChoice == 1){
               System.out.println("Press 1: See current competitors.\n" +
                                  "Press 2: Select new competitors.\n" +
                                  "Press 9: Exit selection menu.");
               int crawlChoice = scan.nextInt();
               scan.nextLine();
               if (crawlChoice == 1){
                  if (crawlComp[0] != null){
                     System.out.println("The selected competitor is:\n" +
                                     crawlComp[0].getName() + "\n");
                  
                  } else if (crawlComp[0] == null){
                     System.out.println("You haven't selected a competitor.\n");
                  }
               } else if (crawlChoice == 2){
                  int selected = 0;
                  do{
                     System.out.println("Enter name:");
                     String name = scan.nextLine();
                     for (int count = 0; count < this.compList.size(); count++){
                        if (compList.get(count).getName().equalsIgnoreCase(name)){
                           crawlComp[selected] = compList.get(count);
                           System.out.println(name + " have been selected.");
                           selected++;
                        }
                     }
                  }while (selected != 1);
               } else if (crawlChoice == 9){
               
               }
               
            // Free competitors
            } else if (selectionChoice == 2){
               System.out.println("Press 1: See current competitor.\n" +
                                  "Press 2: Select new competitor.\n" +
                                  "Press 9: Exit selection menu.");
               int freeChoice = scan.nextInt();
               scan.nextLine();
               if (freeChoice == 1){
                  if (freeComp[0] != null && freeComp[1] != null){
                     System.out.println("The selected competitor is:\n" +
                                     freeComp[0].getName() + "\n");
                  
                  } else if (freeComp[0] == null){
                     System.out.println("You haven't selected a competitor.\n");
                  }
               } else if (freeChoice == 2){
                  int selected = 0;
                  do{
                     System.out.println("Enter name:");
                     String name = scan.nextLine();
                     for (int count = 0; count < this.compList.size(); count++){
                        if (compList.get(count).getName().equalsIgnoreCase(name)){
                           freeComp[selected] = compList.get(count);
                           System.out.println(name + " have been selected.");
                           selected++;
                        }
                     }
                  }while (selected != 1);
               } else if (freeChoice == 9){
               
               }
               
            // Fly competitors
            } else if (selectionChoice == 3){
               System.out.println("Press 1: See current competitor.\n" +
                               "Press 2: Select new competitors.\n" +
                               "Press 9: Exit selection menu.");
               int flyChoice = scan.nextInt();
               scan.nextLine();
               if (flyChoice == 1){
                  if (flyComp[0] != null){
                     System.out.println("The selected competitor is:\n" +
                                     flyComp[0].getName() + "\n");
                  
                  }else if (flyComp[0] == null){
                     System.out.println("You haven't selected a competitor.\n");
                  }
               } else if (flyChoice == 2){
                  int selected = 0;
                  do{
                     System.out.println("Enter name:");
                     String name = scan.nextLine();
                     for (int count = 0; count < this.compList.size(); count++){
                        if (compList.get(count).getName().equalsIgnoreCase(name)){
                           flyComp[selected] = compList.get(count);
                           System.out.println(name + " have been selected.");
                           selected++;
                        }
                     }
                  }while (selected != 1);
               } else if (flyChoice == 9){
               
               }
               
            } else if (selectionChoice == 9){
               selection = false;
            }
         }while (selection);
      // update score
      } else if (choice == 4){
         boolean runUpdate = true;
         do{
            runUpdate = updateScoreMenu();
         } while (runUpdate);
      
      // exit coach menu
      } else if (choice == 9){
         runCoach = false;
      } else {
         System.out.println("Wrong choice.");
      }
      return runCoach;
   }
   
   // Menu for updating the score
   public boolean updateScoreMenu() throws FileNotFoundException {
      boolean runUpdate = true;
      System.out.println("Press 1: Update training score.\n" +
                         "Press 2: Update competitive score.\n" +
                         "Press 9: Exit update.\n");
      Scanner scan = new Scanner(System.in);
      int choice = scan.nextInt();
      scan.nextLine();
      
      // Update training score
      if (choice == 1){
         System.out.println("Enter name:");
         String name = scan.nextLine();
         System.out.println("Enter competition:");
         String comp = scan.nextLine();
         System.out.println("Enter minutes:");
         int minute = scan.nextInt();
         scan.nextLine();
         System.out.println("Enter seconds:");
         int second = scan.nextInt();
         scan.nextLine();
         System.out.println("Enter day:");
         int day = scan.nextInt();
         scan.nextLine();
         System.out.println("Enter month:");
         int month = scan.nextInt();
         scan.nextLine();
         System.out.println("Enter year, 4 digits:");
         int year = scan.nextInt();
         scan.nextLine();
         updateTrainingScore(name, comp, minute, second, day, month, year);
      
      // Update Competition score
      } else if (choice == 2) {
         System.out.println("Enter competition:");
         String comp = scan.nextLine();
         if (comp.equalsIgnoreCase("crawl")){
            if(crawlComp[0] != null) {
               System.out.println("Enter name:");
               String name = scan.nextLine();
               System.out.println("Enter minutes:");
               int minute = scan.nextInt();
               scan.nextLine();
               System.out.println("Enter seconds:");
               int second = scan.nextInt();
               scan.nextLine();
               System.out.println("Enter place:");
               int place = scan.nextInt();
               scan.nextLine();
               for (int count = 0; count < crawlComp.length; count++){
                  if (crawlComp[count].getName().equalsIgnoreCase(name)){
                     crawlComp[count].setCrawlEvent(minute, second, place);
                  }
               }
            } else {
               System.out.println("You must have selected a competitor to update the score");
            }
         } else if (comp.equalsIgnoreCase("free")){
            if(freeComp[0] != null) {
               System.out.println("Enter name:");
               String name = scan.nextLine();
               System.out.println("Enter minutes:");
               int minute = scan.nextInt();
               scan.nextLine();
               System.out.println("Enter seconds:");
               int second = scan.nextInt();
               scan.nextLine();
               System.out.println("Enter place:");
               int place = scan.nextInt();
               scan.nextLine();
               for (int count = 0; count < freeComp.length; count++){
                  if (freeComp[count].getName().equalsIgnoreCase(name)){
                     freeComp[count].setFreeEvent(minute, second, place);
                  }
               }
            } else {
               System.out.println("You must have selected a competitor to update the score");
            }
         } else if (comp.equalsIgnoreCase("fly")){
            if(flyComp[0] != null) {
               System.out.println("Enter name:");
               String name = scan.nextLine();
               System.out.println("Enter minutes:");
               int minute = scan.nextInt();
               scan.nextLine();
               System.out.println("Enter seconds:");
               int second = scan.nextInt();
               scan.nextLine();
               System.out.println("Enter place:");
               int place = scan.nextInt();
               scan.nextLine();
               for (int count = 0; count < flyComp.length; count++){
                  if (flyComp[count].getName().equalsIgnoreCase(name)){
                     flyComp[count].setFlyEvent(minute, second, place);
                  }
               }
            } else {
               System.out.println("You must have selected a competitor to update the score");
            }       
         }
         file.setEventFile(crawlComp, freeComp, flyComp, eventFile);
         
         
      // exit Update menu
      } else if (choice == 9) {
         runUpdate = false;
      } else {
         System.out.println("Wrong choice.");
      }
      return runUpdate;
   }
   public void updateTrainingScore(String name, String comp, int minute, int second, int day, int month, int year){
      for (int count = 0; count < this.compList.size(); count++){
         // searching for the correct competition swimmer
         if (this.compList.get(count).getName().equalsIgnoreCase(name)){
            
            // Checking which competition to set a score in
            if (comp.equalsIgnoreCase("Crawl")){
               this.compList.get(count).compareCrawlTime(minute, second, day, month, year);
            } else if (comp.equalsIgnoreCase("Free")){
               this.compList.get(count).compareFreeTime(minute, second, day, month, year);
            } else if (comp.equalsIgnoreCase("Fly")){
               this.compList.get(count).compareFlyTime(minute, second, day, month, year);
            }
         }
      }
   }
   
   
   
   
   
   
   // Shows the training score.
   public void seeTrainingScore(){
      System.out.println("Score for crawl competitors:");
      for (int count = 0; count < this.compList.size(); count++){
         if (this.compList.get(count).getCrawlComp()){
            this.compList.get(count).getCrawlScore();
         }
      }
      
      System.out.println("Score for free competitors:");
      for (int count = 0; count < this.compList.size(); count++){
         if (this.compList.get(count).getFreeComp()){
            this.compList.get(count).getFreeScore();
         }
      }
      
      System.out.println("Score for fly competitors:");
      for (int count = 0; count < this.compList.size(); count++){
         if (this.compList.get(count).getFlyComp()){
            this.compList.get(count).getFlyScore();
         }
      }
   }
   
   
   
   
   
   
   
   // Cashier Menu
   public boolean cashierMenu(){
      boolean cashierMenu = true;
      System.out.println("Press 1: Change activity level of a member.\n" +
                         "Press 2: Change membership type of a member.\n" +
                         "Press 3: See contingent list for all members.\n" +
                         "Press 4: Add contingent to all members.\n" +
                         "Press 5: Member pays contingent.\n" +
                         "Press 9: Exit cashier menu.");
      Scanner scan = new Scanner(System.in);
      int choice = scan.nextInt();
      scan.nextLine();
      
      // Change activity level
      if (choice == 1) {
         System.out.println("Enter name.");
         Scanner nameScan = new Scanner(System.in);
         String memberName = nameScan.nextLine();
         for (int count = 0; count < casualList.size(); count++){
            if (casualList.get(count).getName().equalsIgnoreCase(memberName)){
               if (casualList.get(count).getLevel()){
                  System.out.println("The Swimmer is an active member." + 
                                              "Changing " + casualList.get(count).getName() + " to passive member.");
                  casualList.get(count).setLevel(false);
                           
               } else {
                  System.out.println("The Swimmer is an passive member." + 
                                              "Changing " + casualList.get(count).getName() + " to active member.");
                  casualList.get(count).setLevel(true);
                        
               } 
            } else if (compList.get(count).getName().equalsIgnoreCase(memberName)){
               if (compList.get(count).getLevel()){
                  System.out.println("The Swimmer is an active member." + 
                                              "Changing " + compList.get(count).getName() + " to passive member.");
                  compList.get(count).setLevel(false);
                           
               } else {
                  System.out.println("The Swimmer is an passive member." + 
                                              "Changing " + compList.get(count).getName() + " to active member.");
                  compList.get(count).setLevel(true);
                        
               } 
            } else {
               System.out.println(memberName + " is not a member of this swimmingclub.");
            }
         }
         
      // change membership type
      } else if (choice == 2) {
         System.out.println("Enter member name:");
         Scanner memberSearch = new Scanner(System.in);
         String memberName = memberSearch.nextLine();
         for (int count = 0; count < casualList.size(); count++){
            if (casualList.get(count).getName().equalsIgnoreCase(memberName)){
               compList.add(new CompSwimmer(compList.get(count).getName(), compList.get(count).getAge(), compList.get(count).getMail(), compList.get(count).getLevel(), compList.get(count).getArrears(), compList.get(count).getCrawlComp(), compList.get(count).getFreeComp(), compList.get(count).getFlyComp()));
               casualList.remove(count);
               System.out.println(memberName + "Has been moved to competitive.\n");
            }
         }
         for (int count = 0; count < compList.size(); count++){
            if (compList.get(count).getName().equalsIgnoreCase(memberName)){
               casualList.add(new Member(compList.get(count).getName(), compList.get(count).getAge(), compList.get(count).getMail(), compList.get(count).getLevel(), compList.get(count).getArrears()));
               compList.remove(count);
               System.out.println(memberName + " has been moved to casual.\n");
            }
         }
      
      
      
      // See total contingent for all members
      } else if (choice == 3) {
         seeContingent();
         
      // add contingent to all members.   
      } else if (choice == 4){
         addContingent();
         
      // member pays contingent
      } else if (choice == 5) {
         contingentPayment();
      
      // Exit cashier menu
      } else if (choice == 9) {
         cashierMenu = false;
      }
      return cashierMenu;
   }
   // See Contingent
   public void seeContingent(){
      int active = 0;
      int junior = 0;
      int senior = 0;
      int seniorUp = 0;
      int passive = 0;
      if (this.casualList.size() != 0){
         for (int count = 0; count < this.casualList.size(); count++){
            if (casualList.get(count).getLevel()){
               active++;
               if (casualList.get(count).getMembership().equalsIgnoreCase("junior")) {
                  junior++;
               } else if (casualList.get(count).getMembership().equalsIgnoreCase("senior")) {
                  senior++;
               } else if (casualList.get(count).getMembership().equalsIgnoreCase("seniorUp")) {
                  seniorUp++;
               }
            } else{
               passive++;
            }
         }
      }
      if (this.compList.size() != 0){
         for (int count = 0; count < this.compList.size(); count++){
            if (compList.get(count).getLevel()){
               active++;
               if (compList.get(count).getMembership().equalsIgnoreCase("junior")) {
                  junior++;
               } else if (compList.get(count).getMembership().equalsIgnoreCase("senior")) {
                  senior++;
               } else if (compList.get(count).getMembership().equalsIgnoreCase("seniorUp")) {
                  seniorUp++;
               }
            } else{
               passive++;
            }
         }
      }
      System.out.println("Number of active members are: " + active + "\n" +
                         "of the active members there are:\n" + 
                         junior + " junior swimmers.\n" +
                         senior + " senior swimmers.\n" +
                         seniorUp + " seniorUp swimmers.\n" +
                         "Number of passive members are: " + passive + ".\n\n");
      for (int count = 0; count < compList.size(); count++){
         System.out.println(this.compList.get(count).getName() + " owes: " + this.compList.get(count).getArrears());
      }
   }
   // members who pay contingent
   public void contingentPayment(){
      System.out.println("Enter name:");
      Scanner scan = new Scanner(System.in);
      String name = scan.nextLine();
      if (this.casualList.size() != 0){
         for (int count = 0; count < casualList.size(); count++){
            if (this.casualList.get(count).getName().equalsIgnoreCase(name)){
               System.out.println(name + " owes: " + this.casualList.get(count).getArrears() + " in contingent.");
               this.casualList.get(count).payArrears();
            }
         }
      }
      if (this.compList.size() != 0){
         for (int count = 0; count < compList.size(); count++){
            if (this.compList.get(count).getName().equalsIgnoreCase(name)){
               System.out.println(name + " owes: " + this.compList.get(count).getArrears() + " in contingent.");
               this.compList.get(count).payArrears();
            }
         }
      }
   }
   // add contingent to all members.
   public void addContingent(){
      if (this.casualList.size() != 0){
         for (int count = 0; count < this.casualList.size(); count++){
            casualList.get(count).addArrears();
         }
      }
      if (this.compList.size() != 0){
         for (int count = 0; count < this.compList.size(); count++){
            compList.get(count).addArrears();
            System.out.println(this.compList.get(count).getName() + " owes: " + this.compList.get(count).getArrears());
         }
      }
      System.out.println("Contingent added to all members.");
   }
   
   
   
   
   
   // Competitive members
   public void seeAllComp(){
      if (this.compList.isEmpty()){
         System.out.println("No existing members on the competitive list.\n");
      } else {
         for (int count = 0; count < this.compList.size(); count++){
            this.compList.get(count).getInfo();
         //For seperation of members
            System.out.println();
         }
      }
   }
   // Casual members
   public void seeAllCasual(){
      if (this.casualList.isEmpty()){
         System.out.println("No existing members on the casual list.\n");
      } else {
         for (int count = 0; count < this.casualList.size(); count++){
            this.casualList.get(count).getInfo();
         //For seperation of members
            System.out.println();
         }
      }
   }
   
   
   
   
   // File methods below...
   
   
   // Competitive files
   public ArrayList<CompSwimmer> compToFile(){
      return this.compList;
   }
   public void compFromFile(ArrayList<CompSwimmer> fileList){
      this.compList = fileList;
   }
   
   // Casual Files
   public ArrayList<Member> casualToFile(){
      return this.casualList;
   }
   public void casualFromFile(ArrayList<Member> fileList){
      this.casualList = fileList;
   }
   
}