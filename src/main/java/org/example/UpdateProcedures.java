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

public class UpdateProcedures {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        Connection Con = null;
        CallableStatement Cstmt = null;
        CallableStatement Cstmtdp = null;
        Scanner sc = new Scanner(System.in);
        //1. Specify the driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2. Specify URL(IP Address/ DB Name/ Username/ Password
        Con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "leo", "leo29798");
        //3. Create statement
        Cstmtdp = Con.prepareCall("{call sp_display(?)}");
        Cstmtdp.registerOutParameter(1, Types.VARCHAR, 25);
        System.out.println(Cstmtdp.getString(1));
        Cstmtdp.executeUpdate();
        //Con.commit();
        System.out.print("Enter the Employee Num to update:");
        int eno = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Job:");
        String job = sc.nextLine();
        System.out.print("Enter the Salary:");
        double sal = sc.nextDouble();
        Cstmt = Con.prepareCall("{call sp_update(?, ?, ?, ?)}");
        Cstmt.setInt(1, eno);
        Cstmt.setDouble(2, sal);
        Cstmt.setString(3, job);
        Cstmt.registerOutParameter(4, Types.VARCHAR, 25);
        // 4. Execute
        Cstmt.executeUpdate();
        System.out.println(Cstmt.getString(4));
        // 5. Close the connection
        Con.close();
    }
}
