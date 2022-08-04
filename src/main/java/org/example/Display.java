package org.example;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class Display {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection Con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Department Number to Display: ");
        int DeptNo = sc.nextInt();
        //Step 1
        //1. Specify the driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2. Specify URL(IP Address/ DB Name/ Username/ Password
        Con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "leo", "leo29798");
        //3. Create statement
        pstmt = Con.prepareStatement("Select * from emp where deptno=?");
        pstmt.setInt(1, DeptNo);
        resultSet = pstmt.executeQuery();
        writeResultSet(resultSet);
        // 4. Execute
        int R = pstmt.executeUpdate();
        System.out.println(R + " rows selected");
        // 5. Close the connection
        resultSet.close();
        pstmt.close();
        Con.close();
    }
    public static void writeResultSet(ResultSet resultSet) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        DecimalFormat df = new DecimalFormat("0.00");
        // ResultSet is initially before the first data set
        System.out.println(String.format("%5s %20s %20s %10s %15s %10s %10s %5s", "EMPNO", "ENAME"
                , "JOB", "MGR", "HIREDATE", "SAL", "COMM", "DEPTNO"));


    }

}