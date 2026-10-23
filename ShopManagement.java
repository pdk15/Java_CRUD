import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ShopManagement{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Electronics";
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
                    
                    System.out.println("Enter Product Name :");
                    String p_name = sc.nextLine();

                    System.out.println("Enter Category :");
                    String category = sc.nextLine();
                    

                    System.out.println("Enter Quantity :");
                    Integer quantity = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Warranty(In months) :");
                    Integer warranty = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Price :");
                    Integer price = sc.nextInt();
                    sc.nextLine();

                   
                    String ins ="insert into Product(Prod_Name , Category , Quantities, Warranty_in_month , Price) value (? , ? , ?, ? ,?)";
                    PreparedStatement pstmt = con.prepareStatement(ins);

                    pstmt.setString(1, p_name);
                    pstmt.setString(2, category);
                    pstmt.setInt(3, quantity);
                    pstmt.setInt(4, warranty);
                    pstmt.setInt(5, price);


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
                    String view = "select * from Product";
                    ResultSet resultSet = stmt.executeQuery(view);

                    while(resultSet.next()){
                        Integer id = resultSet.getInt("Prod_id");
                        String name = resultSet.getString("Prod_Name");
                        String cate = resultSet.getString("Category");
                        Integer quan = resultSet.getInt("Quantities");
                        Integer Warranty = resultSet.getInt("Warranty_in_month");
                        Integer p_price = resultSet.getInt("Price");

                        System.out.println("Id :"+id);
                        System.out.println("Product Name :"+name);
                        System.out.println("Category :"+cate);
                        System.out.println("Quantity :"+quan);
                        System.out.println("Warranty :"+Warranty);
                        System.out.println("Price :"+p_price);


                    }

                    break;

                case 3 :
                    System.out.println("Enter Id :");
                    Integer Id = sc.nextInt();
                    sc.nextLine();
                    String Allview = "select * from Product where Prod_id=?";
                    PreparedStatement  Astmt = con.prepareStatement(Allview);

                    Astmt.setInt(1, Id);
                    ResultSet resultSet2 = Astmt.executeQuery();

                    if(resultSet2.next()){
                        Integer id = resultSet2.getInt("Prod_id");
                        String name = resultSet2.getString("Prod_Name");
                        String cate = resultSet2.getString("Category");
                        Integer quan = resultSet2.getInt("Quantities");
                        Integer Warranty = resultSet2.getInt("Warranty_in_month");
                        Integer p_price = resultSet2.getInt("Price");

                        System.out.println("Id :"+id);
                        System.out.println("Product Name :"+name);
                        System.out.println("Category :"+cate);
                        System.out.println("Quantity :"+quan);
                        System.out.println("Warranty :"+Warranty);
                        System.out.println("Price :"+p_price);

                    }
                    else{
                        System.out.println("\nRecord not found!");
                    }
                                      
                break;


                case 4 :

                    System.out.println("Enter ID :");
                    Integer p_id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Product Name :");
                    String P_name = sc.nextLine();

                    System.out.println("Enter Category :");
                    String P_category = sc.nextLine();
                    

                    System.out.println("Enter Quantity :");
                    Integer P_quantity = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Warranty(In months) :");
                    Integer P_warranty = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Price :");
                    Integer P_price = sc.nextInt();
                    sc.nextLine();


                    
                    String update = "update Product set Prod_Name = ? , Category = ? , Quantities = ?, Warranty_in_month = ? , Price = ? where Prod_id=?";
                    PreparedStatement ustmt = con.prepareStatement(update);

                    
                    ustmt.setString(1,P_name);
                    ustmt.setString(2, P_category);
                    ustmt.setInt(3, P_quantity);
                    ustmt.setInt(4, P_warranty);
                    ustmt.setInt(5, P_price);
                    ustmt.setInt(6, p_id);


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

                    
                    String del = "delete from Product where Prod_id=?";
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