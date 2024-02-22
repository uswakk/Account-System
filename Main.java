//20I-0809
//Uswa Khan
 
import java.lang.String;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileWriter; 
import java.io.IOException;  
       
abstract class Accounts
{
    private String AccountNumber = "";
    public double balance;
    private String dateCreated = "";
    private char type; //for simplicity sake user can either make a savings or a checkings account

    void setType(char type)
    {
        this.type = type;
    }

    char getType()
    {
        return type;
    }
    public String getAccNum()
    {
        return AccountNumber;
    }
   
    public String getDateCreated()
    {
        return dateCreated;
    }
   
    public double getBalance()
    {
        return balance;
    }
   
    void setAccNumber(String AccountNumber)
    {
        this.AccountNumber = AccountNumber;
    }
   
    void setBalance(double balance)
    {
        this.balance=balance;
    }
   
    void setDateCreated(String dateCreated)
    {
        this.dateCreated = dateCreated;
    }
   
    void makeDeposit(double deposit)
    {

    }
     
    void makeWithdrawal(double amount)
    {
        
    }

     void deduceAllDeductions()
    {
        //check deductions here
    }
   
    void calculateInterest(int time){}
    //time is in years
 
    void calculateInterest(float rate, int time){} 
}

class Savings extends Accounts
{
    double Zakat = 0;
    double interest = 0;
    float rateInterest = 2.5f; 
    
    double calculateZakat()
    {
        if(balance >= 20000)
        {
            Zakat = (2.5*this.getBalance())/100;
        }
        
        return Zakat;
    }
    
   void makeDeposit(double amount)
   {
        this.balance+=amount;
   }
   
   void makeWithdrawal(double amount)
   {
       //implement some check
        if(amount > balance)
        {
            System.out.println("Insufficient Balance");
        }
        else
        {
            this.balance = balance - amount;
        }
       
   }

   void calculateInterest(int time)//time is in years
   {
	interest = balance*time*rateInterest;
	System.out.printf("%Interest Is: %", interest);
   }

   void calculateInterest(float rate, int time)
   {
	interest = balance*rate*time;
   }
   
       void deduceAllDeductions()
    {
        //check deductions here
        System.out.printf("%Existing Balance: %", getBalance());
        double deductions = Zakat + interest;
        System.out.printf("%Deductions: %", deductions);
        this.setBalance(getBalance() - deductions);
        System.out.printf("%Remaining Balance: %", getBalance());

       // return deductions;
    }
}

class Checking extends Accounts
{
    int freetransactionleft = 2;
    int totalTranstionFee = 0;
    
    
    
   void makeWithdrawal(double amount)
   {
       if(amount <= 5000)
       if(freetransactionleft !=0 )
       {
            balance = balance - amount;
            freetransactionleft--; //transaction's left is reduced
       }
       else
       {
           balance = balance - amount;
           totalTranstionFee+=10;
           
       }
       else
       {
           System.out.println("InValid Amount");
       }
      
   }
   
    void deduceAllDeductions()
    {
        //check deductions here
        System.out.printf("%Existing Balance: %", getBalance());
        double deductions = totalTranstionFee;
        System.out.printf("%Deductions: %", deductions);
        this.setBalance(getBalance() - deductions);
        System.out.printf("%Remaining Balance: %", getBalance());

       // return deductions;
    }
   
}

class User
{
    private String Name = "";
    private int ID = 0;
    private String address = "";
    private String phoneNumber = "";
    public Accounts usersAccount;
    //making a pointer to have an array
    //so that the user can have upto 2 types of accounts

    public User(){}
    String getName()
    {
        return Name;
    }
   
    int getID()
    {
        return ID;
    }
   
    void setName(String Name)
    {
        this.Name = Name;
    }
   
    void setID(int ID)
    {
        this.ID=ID;
    }
   
    void setAddress(String address)
    {
        this.address = address;
    }
   
    String getAddress()
    {
        return address;    
    }
    
    String getphoneNumber()
    {
        return this.phoneNumber;
    }
    
    void setPhoneNumber(String number)
    {
        this.phoneNumber = number;
    }
   
    void setAccount(String accNum, String dateCreated, double balance, int i)
    {
        if(i == 0)
        {
            usersAccount = new Checking();
        }
        else
        {
            usersAccount = new Savings();
        }
        usersAccount.setAccNumber(accNum);
        usersAccount.setDateCreated(dateCreated);


        usersAccount.setBalance(balance);
    }
    
    //helper functions//help with the functionality//
    int validateAccountNumber(String accNum) //same function 
    {
        int valid = 1; //returns 1 if account number is valid 
        //any other number is invalid 
        int number = 10; //10 is easier to input
        if(accNum.length() != number)
        {
            valid = 0;
        }
        if(accNum.charAt(0)!='P'&& accNum.charAt(1)!='K'  )
        {
            valid = 0;
        }
        return valid;

    }

    public static int validatePhoneNumber(String phoneNum)
    {
        int valid = 1; //returns 1 if account number is valid 
        //any other number is invalid 
        int number = 11; //10 is easier to input
        if(phoneNum.length() != number)
        {
            valid = 0;
        }
        if(phoneNum.charAt(0)!='0'&& phoneNum.charAt(1)!='3'  )
        {
            valid = 0;
        }
        return valid;
    }

    public static int checkExistingAccounts(String str)
    {
        int valid = 1;
        int num = 50;
        String[] accNums = new String[num];
        
        try
        {
            File obj = new File("AccountNumbers.txt");
            Scanner myReader = new Scanner(obj);
            int i = 0;
            while(myReader.hasNextLine() && i < num)
            {
                accNums[i] = myReader.nextLine();
                i++;
            }

            int numOfEntrees = i;
            myReader.close();

            for(i = 0; i <= numOfEntrees; i++ )
            {
                //System.out.println(accNums[i]);
                if(str.equals(accNums[i]))
                {
                    valid = 0;
                    break;
                }
            }

            return valid;

        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return valid;
    }


    void makeAccount()
    {
        System.out.println("****Creating A New Account*****");
        Scanner obj = new Scanner(System.in);  // Create a Scanner object
        Scanner obj2 = new Scanner(System.in);  // Create a Scanner object
        Scanner obj3 = new Scanner(System.in);
        Scanner obj4 = new Scanner(System.in);

        System.out.print("Input Name: ");
        String Name = obj4.nextLine();

        System.out.print("Input Account Number: ");
        String accnum = obj.nextLine();
        while(validateAccountNumber(accnum) != 1 || checkExistingAccounts(accnum)!=1)
        {
            System.out.println("Invalid! Enter Again");
            accnum = obj.nextLine();
        }

        System.out.print("Input Type (0 for Checking, 1 for Savings)): ");
        int num = obj.nextInt();
        
        while(num!=1 && num!= 0)
        {
            System.out.println("InValid! Enter Again!");    
            num = obj.nextInt();
        }
        
        System.out.print("Input Date: ");
        String date = obj2.nextLine();
        System.out.print("Input Balance: ");
        double Balance = obj.nextDouble();
        System.out.print("Input Phone Number: ");
        String phoneNumber = obj3.nextLine();
        while(validatePhoneNumber(phoneNumber) != 1)
        {
            System.out.println("InValid Phone Number! Enter Again!");
            phoneNumber = obj3.nextLine();

        }
        //first create an object of account type 
        if(num == 0)
        {
            this.usersAccount = new Checking();
            this.usersAccount.setType('C');
        }
        else if (num == 1)
        {
            this.usersAccount = new Savings();
            this.usersAccount.setType('S');

        }

        this.Name = Name;
        this.usersAccount.balance = Balance;
        this.usersAccount.setAccNumber(accnum);
        this.usersAccount.setDateCreated(date);
        this.phoneNumber = phoneNumber;
        System.out.println("");
        System.out.println("****Account Successfully Created*****");


        obj.close();
        obj2.close();
        obj3.close();
        obj4.close();
    }
    void getAccount()
    {
	  System.out.println("Account Number: " + usersAccount.getAccNum());
	  System.out.println("Date Created: " +  usersAccount.getDateCreated());
	  System.out.printf("%Balance: %", usersAccount.getBalance());	
    }

    void printStatement()
    {
	  System.out.println("Name: " + this.getName());
	  System.out.printf("%ID: %",  this.getID());
	  System.out.println("Address: " + this.getAddress());
	   System.out.println("Account Number: " + usersAccount.getAccNum());
	  System.out.println("Date Created: " +  usersAccount.getDateCreated());
	  System.out.printf("%Balance: %", usersAccount.getBalance());	

	  
    }
   
}

/*class Transaction 
{
    private String date;
    private String time;
    //amount and balance//
    //private User user;//is this needed?
    //work on it is important 
   
}*/


class Main {
       
    //transferAmount 
    //int value = 0;    
    public static void transferAmount(User user1, User user2)
    {
        
        Scanner In= new Scanner(System.in);
        //transfers amount from one bank to another
        System.out.println("Input Amount: ");
        double Amount = In.nextDouble();
        transferAmountHelper(user1, user2, Amount);
        In.close();
    }    

    public static void transferAmountHelper(User user1, User user2, double amount)
    {
   	    user1.usersAccount.makeWithdrawal(amount);
	    user1.usersAccount.makeDeposit(amount); //checking account lies at index 0
	    System.out.println("Transfer Successful!");
    }

    public static int validateAccountNumber(String accNum)
    {
        int valid = 1; //returns 1 if account number is valid 
        //any other number is invalid 
        int number = 10; //10 is easier to input
        if(accNum.length() != number)
        {
            valid = 0;
        }
        if(accNum.charAt(0)!='P'&& accNum.charAt(1)!='K'  )
        {
            valid = 0;
        }
        return valid;
    }

    public static void updateDatabase(Accounts acc, User user, int value)
    {
        //File Obj = new File("Accounts.txt"); 
        
        try
        {
            FileWriter Writer;
            if(value == 0)
            {
                Writer = new FileWriter("Accounts.txt"); //don't append

            }
            else
            {
                Writer = new FileWriter("Accounts.txt", true); //append

            }
            Writer.write(acc.getAccNum());
            Writer.write(".");
            Writer.write(acc.getDateCreated());
            Writer.write(".");
            Writer.write(user.getName());
            Writer.write(".");
            Writer.write(user.getAddress());
            Writer.write(".");
            Writer.write(user.getphoneNumber());
            Writer.write("!\n"); //exclamation mark marks the end of a data value 
            Writer.close();
     
        }
        catch(IOException e)
        {
            System.out.println("Error! Can't update DataBase"); 
        }

    }

    public static void updateAccNumbers(Accounts acc, int value)
    {
        try
        {
            FileWriter WriterAccNum;
            if(value == 0)
            {
                WriterAccNum = new FileWriter("AccountNumbers.txt"); //don't append

            }
            else
            {
                WriterAccNum = new FileWriter("AccountNumbers.txt", true); //append

            }
            WriterAccNum.write(acc.getAccNum() + "\n");
            WriterAccNum.close();
     
        }
        catch(IOException e)
        {
            System.out.println("Error! Can't update DataBase"); 
        }

    }

    
    public static void main(String[] args) {
 
        User existing = new User();
        existing.usersAccount = new Savings();
        existing.setName("Uswa");
        existing.setPhoneNumber("03145118947");
        existing.setAddress("Islamabad");
        existing.setAccount("PK00456789", "18/09/2007", 1500000, 1); //savings account
        existing.setID(1); 

        User existing1 = new User();
        existing1.usersAccount = new Checking();
        existing1.setName("Resham Khan");
        existing1.setPhoneNumber("03005818947");
        existing1.setAddress("Lahore");
        existing1.setAccount("PK00156789", "19/09/2007", 1500890, 2); //savings account
        existing1.setID(2); 

       //updateDatabase(existing.usersAccount, existing, 0);
        //updateDatabase(existing1.usersAccount, existing1, 1);
        //updateAccNumbers(existing.usersAccount, 1);
        //updateAccNumbers(existing1.usersAccount, 1);
        
        User admin = new User();
        Scanner IN = new Scanner(System.in);
        System.out.println("******************************");
        System.out.println("1. Create An Account ");
        System.out.println("2. Login");
        System.out.println("******************************");
        int choice = IN.nextInt();
        
        while(choice < 0 || choice > 2)
        {
            System.out.println("Error! InValid Choice!");
        }
        if(choice == 1)
        {
            //handles the creation of account//
            admin.makeAccount();
            updateDatabase(admin.usersAccount, admin, 2);
            updateAccNumbers(admin.usersAccount, 1);
            
        }
        else
        {
            Scanner In = new Scanner(System.in);
            System.out.print("Account Number: ");
            String accNum = In.nextLine();

            while( ( accNum.equals(existing.usersAccount.getAccNum()) ) == false || validateAccountNumber(accNum) != 1) //hard coded
            {
                System.out.println("Invalid! Enter Again");
                accNum = In.nextLine();
            }
            
            System.out.println("***Welcome!***");
            System.out.print("Withdrawal Amount: ");
            double amount = In.nextDouble();
            existing.usersAccount.makeWithdrawal(amount);
        
        
            System.out.print("Deposit Amount: ");
            amount = In.nextDouble();
            existing.usersAccount.makeDeposit(amount);
            
            System.out.println("");
          //  System.out.printf("%Your total Zakat is:   %" + existing.usersAccount.calculateZakat());
            
            System.out.println("Do you want to transfer money? (1 for Yes)");
            choice = In.nextInt();
            if(choice == 1)
            {
                transferAmount(existing, existing1);
            }
    
            //existing.usersAccount.deduceAllDeductions();
            //existing.printStatement();//prints everything

            /*System.out.println("Enter Time And Rate to Calculate Interest");
            int time = In.nextInt();
            float rate = In.nextFloat();
            existing.usersAccount.calculateInterest(rate, time);*/

 
            In.close();

            IN.close();
        }
    }    
    
};

