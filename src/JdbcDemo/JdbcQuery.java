package JdbcDemo;

import java.sql.*;
import java.util.Scanner;

public class JdbcQuery {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","123456");
            System.out.println("连接状态："+!connection.isClosed());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
////        System.out.println("请输入要查询的学生的姓名：");
////        Scanner scanner = new Scanner(System.in);
//        String str = scanner.next();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
//            String sql = "select studentNo,studentName from student where studentName= '"+str+"'";
            String sql = "select studentNo,studentName,phone from student ";
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            int i;
            String name;
            while (resultSet.next()){
                i = resultSet.getInt("studentNo");
//                resultSet.getInt(1);
                name = resultSet.getString("studentName");
                System.out.println("no="+i+", name="+name);
            }
            resultSet.last();
            System.out.println("行数："+resultSet.getRow());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }
}
