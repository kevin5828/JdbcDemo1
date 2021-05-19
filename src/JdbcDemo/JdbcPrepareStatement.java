package JdbcDemo;

import java.sql.*;
import java.util.Scanner;

public class JdbcPrepareStatement {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","123456");
//            System.out.println("连接状态："+!connection.isClosed());

//            statement = connection.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet resultSet = null;
        try {
            while (true) {
                System.out.println("请输入登陆的学号：");
                Scanner scanner = new Scanner(System.in);
                String no = scanner.next();
                System.out.println("请输入登陆密码：");
                String pswd = scanner.next();

                //1234‘ or '1'='1
//                String sql = "select studentNo,studentName from student where studentNo=" + no + " and loginPwd='" + pswd + "'";
                //使用prepareStatement写法
                String sql = "select studentNo,studentName from student where studentNo=?" + " and loginPwd=?";

                System.out.println(sql);
//                resultSet = statement.executeQuery(sql);
                statement1 = connection.prepareStatement(sql);
                statement1.setString(1,no);
                statement1.setString(2,pswd);
                //Statement没有setString()方法，只能使用PreparedStatement
//                statement.setString
                resultSet = statement1.executeQuery();
                resultSet.last();
                System.out.println("行数：" + resultSet.getRow());
                if (resultSet.getRow() > 0) {
                    System.out.println("欢迎登陆学生管理系统！");
                    break;
                } else {
                    System.out.println("输入的学号或者密码错误！");
                    continue;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                resultSet.close();
                statement1.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
