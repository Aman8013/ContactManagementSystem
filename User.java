import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private final Connection connection;
    private final Scanner scanner;
    User(Connection connection, Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public void addTheContact() throws InterruptedException {
        scanner.nextLine();
        System.out.println("Enter the Mobile Number");
        String mobileNumber=scanner.nextLine();
        System.out.println("Enter the First Name");
        String firstName=scanner.nextLine();
        System.out.println("Enter the Last Name");
        String lastName=scanner.nextLine();
        while(check(firstName,lastName)){
            System.out.println("This Name Already Exists.");
            Thread.sleep(450);
            System.out.println("Try Other Name");
            Thread.sleep(450);
            System.out.println("Enter the first Name");
            firstName=scanner.nextLine();
            System.out.println("Enter the Last Name");
            lastName=scanner.nextLine();
        }
        try{
            String query="Insert into contact(firstName,lastName,MobileNumber) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,mobileNumber);
            int rows= preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("Contact Successfully Saved");
            }else{
                System.out.println("Failed to save the Contact");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void updateTheContact(){
           System.out.println("1. Change the Mobile Number");
           System.out.println("2. Change the Name");
           System.out.println("3. Exit");
           System.out.println("Enter the Choice");
           int choice=scanner.nextInt();
           switch (choice){
               case 1:
                      changeTheMobileNumber();
                      break;
               case 2:
                      changeTheName();
                      break;
               case 3:
                      return;
               default:
                     System.out.println("Invalid choice");
           }
    }
    public void deleteTheContact(){
           System.out.println("Enter the Mobile Number");
           String mobileNumber=scanner.nextLine();
           String query="delete from contact where MobileNumber=?";
           try{
               PreparedStatement preparedStatement= connection.prepareStatement(query);
               preparedStatement.setString(1,mobileNumber);
               ResultSet resultSet= preparedStatement.executeQuery();
               if(resultSet.next()){
                   System.out.println("Successfully deleted");
               }else{
                   System.out.println("Contact doesn't  Exit");
               }
           }catch(SQLException e){
               System.out.println(e.getMessage());
           }
    }
    public void viewTheContact(){
        String query="Select * from contact order by firstName,lastName";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("ID"+"    "+"FirstName"+"    "+"LastName"+"    "+"MobileNumber");
            while(resultSet.next()){
              int id=resultSet.getInt("id");
              String firstName=resultSet.getString("firstName");
              String lastName=resultSet.getString("lastName");
              String MobileNumber=resultSet.getString("MobileNumber");
              System.out.println(id+"       "+firstName+"       "+lastName+"       "+MobileNumber);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public boolean check(String firstName,String lastName){
        String query="Select * from contact where firstName=? and lastName=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            ResultSet resultSet= preparedStatement.executeQuery();
            return resultSet.next();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void changeTheMobileNumber(){
        scanner.nextLine();
        System.out.println("Enter the New Mobile Number");
        String mobileNumber=scanner.nextLine();
        System.out.println("Enter the first Name");
        String firstName=scanner.nextLine();
        System.out.println("Enter the last Name");
        String lastName=scanner.nextLine();
        String query="UPDATE contact SET MobileNumber=? where firstName=? and lastName=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,mobileNumber);
            preparedStatement.setString(2,firstName);
            preparedStatement.setString(3,lastName);
            int rows= preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("Successfully Updated");
            }else{
                System.out.println("Failed to Update");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void  changeTheName(){
        scanner.nextLine();
        System.out.println("Enter the Mobile Number");
        String mobileNumber=scanner.nextLine();
        System.out.println("Enter the New first Name");
        String firstName=scanner.nextLine();
        System.out.println("Enter the New last Name");
        String lastName=scanner.nextLine();
        String query="UPDATE contact set firstName=?,lastName=? where MobileNumber=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,mobileNumber);
            int rows= preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("Successfully Updated");
            }else{
                System.out.println("Failed to Update");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
