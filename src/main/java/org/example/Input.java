package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection Con = null;
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee No:");
        int eno = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Employee Name");
        String Ename = sc.nextLine();
        System.out.println("Enter Gender");
        String Gender = sc.nextLine();
        System.out.println("Enter DOB(dd-MMM-yyyy)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate DOB = LocalDate.parse(sc.nextLine(), formatter);
        System.out.println("Enter SSN");
        String SSN = sc.nextLine();
        System.out.println("Enter DOJ(dd-MMM-yyyy)");
        LocalDate DOJ = LocalDate.parse(sc.nextLine(), formatter);
        System.out.println("Enter email");
        String email = sc.nextLine();
        System.out.println("Enter DeptID");
        int Deptid = sc.nextInt();
        //Step 1
        //1. Specify the driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2. Specify URL(IP Address/ DB Name/ Username/ Password
        Con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "leo", "binpro321");
        //3. Create statement
        pstmt = Con.prepareStatement("Insert into Employee values(?, ?, ?, ?, ?, ?, ?, ?)");
        pstmt.setInt(1, eno);
        pstmt.setString(2, Ename);
        pstmt.setString(3, Gender);
        pstmt.setString(4, formatter.format(DOB));
        pstmt.setString(5, SSN);
        pstmt.setString(6, formatter.format(DOJ));
        pstmt.setString(7, email);
        pstmt.setInt(8, Deptid);
        // 4. Execute
        int R = pstmt.executeUpdate();
        System.out.println(R + " rows inserted");
        // 5. Close the connection
        Con.close();
        }


    }