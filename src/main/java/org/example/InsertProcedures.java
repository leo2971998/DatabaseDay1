package org.example;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Scanner;
import java.util.*;

public class InsertProcedures {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        Connection Con = null;
        CallableStatement Cstmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Employee num:");
        int eno = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Ename:");
        String ename = sc.nextLine();
        System.out.print("Enter the Job:");
        String job = sc.nextLine();
        System.out.print("Enter the MGR:");
        int Mgr = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Hire date:");
        LocalDate hiredate = LocalDate.parse(sc.nextLine(), formatter);
        System.out.print("Enter the Salary:");
        double sal = sc.nextDouble();
        System.out.print("Enter the Comm:");
        double comm = sc.nextDouble();
        System.out.print("Enter the Department No:");
        int DeptNo = sc.nextInt();
        //1. Specify the driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2. Specify URL(IP Address/ DB Name/ Username/ Password
        Con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "leo", "leo29798");
        //3. Create statement
        //Con.commit();
        Cstmt = Con.prepareCall("{call sp_insert(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        Cstmt.setInt(1, eno);
        Cstmt.setString(2, ename);
        Cstmt.setString(3, job);
        Cstmt.setInt(4, Mgr);
        Cstmt.setDate(5,  java.sql.Date.valueOf(hiredate));
        Cstmt.setDouble(6, sal);
        Cstmt.setDouble(7, comm);
        Cstmt.setInt(8, DeptNo);
        Cstmt.registerOutParameter(9, Types.VARCHAR, 25);
        // 4. Execute
        Cstmt.executeUpdate();
        System.out.println(Cstmt.getString(9));
        // 5. Close the connection
        Con.close();
    }
}
