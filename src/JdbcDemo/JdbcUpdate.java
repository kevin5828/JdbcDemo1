package JdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUpdate {
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
        Statement statement = null;
        try {
            statement = connection.createStatement();
//            update `subject` set classHour=classHour+50 WHERE subjectNo = 5;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("update `subject` set classHour=classHour+50 WHERE subjectNo = 5");
            System.out.println(stringBuffer);

            //方法2
            int r = statement.executeUpdate(stringBuffer.toString());
            System.out.println("返回更新的条数="+r);

            //练习1：接受键盘输入的id和手机号码，更新userInfo表的手机号码字段
            //练习2：接受键盘输入的id,删除此customerId记录

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
