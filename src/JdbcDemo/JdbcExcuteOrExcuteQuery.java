package JdbcDemo;

import java.sql.*;

public class JdbcExcuteOrExcuteQuery {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","123456");
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
            String sql="";
//            sql = "select studentNo,studentName from student ";
//            sql = "insert student(studentNo,studentName) values (11,'aa') ";
            System.out.println(sql);

            boolean b =  statement.execute(sql);
            int cnt = 0;
            if(sql.startsWith("select") && b ){
                resultSet = statement.getResultSet();
            }else {
                cnt = statement.getUpdateCount();
            }

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
