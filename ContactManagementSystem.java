import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class ContactManagementSystem {
    static  final  String url = "jdbc:mysql://localhost:3306/contact";
    static  final String username="root";
    static  final String password="Gaya@123";

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
         try{
             Class.forName("com.mysql.cj.jdbc.Driver");
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
         try{
             Connection connection= DriverManager.getConnection(url,username,password);
             Scanner scanner=new Scanner(System.in);
             User user=new User(connection,scanner);
             while(true){
                 System.out.println();
                 System.out.println();
                 System.out.println("Contact Management System");
                 System.out.println();
                 System.out.println("1. Add the Contact");
                 System.out.println("2. Update the Contact");
                 System.out.println("3. Delete the Contact");
                 System.out.println("4. View the Contact");
                 System.out.println("5. Exit");
                 System.out.println();
                 System.out.println("Enter the Choice");
                 int choice=scanner.nextInt();
                 switch (choice){
                     case 1:
                            user.addTheContact();
                            break;
                     case 2:
                            user.updateTheContact();
                            break;
                     case 3:
                            user.deleteTheContact();
                            break;
                     case 4:
                            user.viewTheContact();
                            break;
                     case 5:
                           exit();
                           return;
                     default:
                            System.out.println("Invalid Choice");
                 }
             }
         }catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }
    public static void exit() throws InterruptedException{
        System.out.println("EXIT");
        int i=5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
    }
}
