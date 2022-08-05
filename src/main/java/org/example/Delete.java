package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection Con = null;
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Employee Number to delete:");
        int Empno = sc.nextInt();
        //Step 1
        //1. Specify the driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2. Specify URL(IP Address/ DB Name/ Username/ Password
        Con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "leo", "leo29798");
        //3. Create statement
        pstmt = Con.prepareStatement("Delete from employee where empno=?");
        pstmt.setInt(1, Empno);
        // 4. Execute
        int R = pstmt.executeUpdate();
        System.out.println(R + " rows deleted");
        // 5. Close the connection
        Con.close();
    }


}