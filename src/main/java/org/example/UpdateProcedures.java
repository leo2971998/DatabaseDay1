package org.example;

import oracle.ucp.proxy.annotation.Pre;

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
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Scanner sc = new Scanner(System.in);
        //1. Specify the driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2. Specify URL(IP Address/ DB Name/ Username/ Password
        Con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "leo", "leo29798");
        //3. Create statement
        Con.setAutoCommit(false);
        Con.commit();
        pstmt = Con.prepareStatement("Select empno, ename, sal, job from emp");
        resultSet = pstmt.executeQuery();
        writeResultSet(resultSet);
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
        System.out.println("After update: ");
        pstmt = Con.prepareStatement("Select empno, ename, sal, job from emp");
        resultSet = pstmt.executeQuery();
        writeResultSet(resultSet);
        // 5. Close the connection
        Con.close();
        resultSet.close();
    }
    public static void writeResultSet(ResultSet resultSet) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        DecimalFormat df = new DecimalFormat("0.00");
        // ResultSet is initially before the first data set
        System.out.println(String.format("%5s %20s %20s %20s", "EMPNO", "ENAME", "SAL", "JOB"));
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            int Empno = resultSet.getInt("EMPNO");
            String Ename = resultSet.getString("ENAME");
            String Job = resultSet.getString("JOB");
            double sal = resultSet.getDouble("SAL");
            System.out.println(String.format("%5s %20s %20s %20s", Empno, Ename, df.format(sal), Job));
        }
    }
}
