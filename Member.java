public class Member{
   private String name;
   private int age;
   private String mail;
   private String membership;
   private double discount;
   private boolean activityLevel;
   private int arrears;
   
   public Member(String name, int age, String mail, boolean level, int arrears){
      this.name = name;
      this.age = age;
      this.mail = mail;
      this.discount = 0.75;
      this.activityLevel = level;
      this.arrears = arrears;
      membershipPayment();
   }
   
   private String membershipPayment(){
      if (age > 0 && age < 18){
         membership = "Junior";
      } else if (age >= 18 && age < 60){
         membership = "Senior";
      } else if (age >= 60){
         membership = "SeniorUp";
      } else {
         System.out.println("Not an accepted age.");
      }
      return membership;
   }
   public int payment(){
      int price = 0;
      membershipPayment();
      
      if (activityLevel){
         if (membership.equalsIgnoreCase("Junior")){
            price = 1000;
         } else if (membership.equalsIgnoreCase("Senior")) {
            price = 1600;
         } else if (membership.equalsIgnoreCase("SeniorUp")){
            double tempPrice = 1600 * discount;
            price = (int) tempPrice;
         }
      } else {
         price = 500;
      }
      return price;
   }
   
   
   
   
   
   
   public void payArrears(){
      this.arrears = 0;
   }
   public void addArrears(){
      this.arrears += payment();
   }
   public int getArrears(){
      return this.arrears;
   }
   public void setLevel(boolean level){
      this.activityLevel = level;
   }
   public String getMembership(){
      return this.membership;
   }
   public String getName(){
      return this.name;
   }
   public int getAge(){
      return this.age;
   }
   public String getMail(){
      return this.mail;
   }
   public boolean getLevel(){
      return this.activityLevel;
   }
   public void getInfo(){
      System.out.println("Navn: " + name + "\nAlder: " + age + "\nE-Mail: " + mail + "\n" + name + " skal betale: " + payment());
   }
   
}