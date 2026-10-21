import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentManagement{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student";
            String username = "root";
            String password = "root";
        try {
            Connection con = DriverManager.getConnection(url , username , password);
            
            Scanner sc = new Scanner(System.in);
                int choice ;

            do {
                System.out.println("\n1. Insert record");
                System.out.println("2. View all records");
                System.out.println("3. View specific record");
                System.out.println("4. Update perticular record ");
                System.out.println("5. Delete record");
                System.out.println("6. Exit application");
                System.out.println("Enter your operation : (1 to 6)");

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                case 1:
                    
                    System.out.println("Enter Name :");
                    String name = sc.nextLine();

                    System.out.println("Enter Age :");
                    Integer age = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Course :");
                    String course = sc.nextLine();

                   
                    String ins ="insert into stud(name , age , course) value (? , ? , ?)";
                    PreparedStatement pstmt = con.prepareStatement(ins);

                    pstmt.setString(1, name);
                    pstmt.setInt(2, age);
                    pstmt.setString(3, course);

                    int row=pstmt.executeUpdate();

                    if(row >0)
                    {
                        System.out.println("\nRecord Inserted...!");
                        
                    }
                    else{
                        System.out.println("\nFailed to insert record");
                    }
                    break;

                case 2 :
                    
                    Statement stmt = con.createStatement();
                    String view = "select * from stud";
                    ResultSet resultSet = stmt.executeQuery(view);

                    while(resultSet.next()){
                        Integer id = resultSet.getInt("id");
                        String Name = resultSet.getString("name");
                        Integer Age = resultSet.getInt("age");
                        String Course = resultSet.getString("course");

                        System.out.println("Id :"+id);
                        System.out.println("Name :"+Name);
                        System.out.println("Age :"+Age);
                        System.out.println("Course :"+Course);
                    }

                    break;

                case 3 :
                    System.out.println("Enter Id :");
                    Integer Id = sc.nextInt();
                    sc.nextLine();
                    String Allview = "select * from stud where id=?";
                    PreparedStatement  Astmt = con.prepareStatement(Allview);

                    Astmt.setInt(1, Id);
                    ResultSet resultSet2 = Astmt.executeQuery();

                    if(resultSet2.next()){
                        Integer id = resultSet2.getInt("id");
                        String Name = resultSet2.getString("name");
                        Integer Age = resultSet2.getInt("age");
                        String Course = resultSet2.getString("course");

                        System.out.println("\nId :"+id);
                        System.out.println("Name :"+Name);
                        System.out.println("Age :"+Age);
                        System.out.println("Course :"+Course);
                    }
                    else{
                        System.out.println("\nRecord not found!");
                    }
                                      
                break;


                case 4 :
                    System.out.println("Enter ID : ");
                    Integer u_Id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Name :");
                    String Newname = sc.nextLine();

                    System.out.println("Enter Age :");
                    Integer Newage = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Course :");
                    String Newcourse = sc.nextLine();

                    
                    String update = "update stud set name = ? , age = ? , course = ? where id=?";
                    PreparedStatement ustmt = con.prepareStatement(update);

                    
                    ustmt.setString(1,Newname);
                    ustmt.setInt(2, Newage);
                    ustmt.setString(3, Newcourse);
                    ustmt.setInt(4, u_Id);

                    int Ucheck =ustmt.executeUpdate();

                    if (Ucheck > 0){
                        System.out.println("\nRecord updated successfully...! ");
                    }
                    else{
                        System.out.println("\nNo record  found");
                    }
                    
                    
                break ;


                case 5 :
                    System.out.println("\nEnter ID : ");
                    Integer d_Id = sc.nextInt();
                    sc.nextLine();

                    
                    String del = "delete from stud where id=?";
                    PreparedStatement dstmt = con.prepareStatement(del);
                    
                    dstmt.setInt(1, d_Id);
                    int Dcheck =dstmt.executeUpdate();

                    if (Dcheck > 0){
                        System.out.println("\nRecord "+d_Id+" deleted successfully...! ");
                    }
                    else{
                        System.out.println("\nNo record at " +d_Id+ " found");
                    }
                break ;


                case 6 :
                    System.out.println("Exit");
                break ;
                
                default:
                break;
            }
                
            } while (choice != 6);     
            con.close();

        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
}