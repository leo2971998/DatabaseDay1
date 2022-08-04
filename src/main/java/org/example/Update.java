package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection Con = null;
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Employee Number to update: ");
        int Empno = sc.nextInt();
        System.out.println("Enter DeptID");
        int Deptid = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Email: ");
        String email = sc.nextLine();
        //Step 1
        //1. Specify the driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2. Specify URL(IP Address/ DB Name/ Username/ Password
        Con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "leo", "leo29798");
        //3. Create statement
        pstmt = Con.prepareStatement("Update employee set deptid=?,email=? Where empno=?");
        pstmt.setInt(3, Empno);
        pstmt.setInt(1, Deptid);
        pstmt.setString(2, email);
        // 4. Execute
        int R = pstmt.executeUpdate();
        System.out.println(R + " rows updated");
        // 5. Close the connection
        Con.close();
    }


}