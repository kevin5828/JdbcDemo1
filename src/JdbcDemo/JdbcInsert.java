package JdbcDemo;

import java.sql.*;

public class JdbcInsert {
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
//        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
//            String sql = "select studentNo,studentName from student where studentName= '"+str+"'";
//            String sql = "select studentNo,studentName from student ";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("insert into subject(subjectNo,subjectName,classHour,gradeID) ");
            stringBuffer.append("values");

//            (4,'css',200,1)
            stringBuffer.append("(");
            stringBuffer.append("4,");//subjectNo
            stringBuffer.append("'css',");//subjectName
            stringBuffer.append("200,");//classHour
            stringBuffer.append("1");//gradeID
            stringBuffer.append(")");

            stringBuffer.append(",");

            //            (5,'js',100,1)
            stringBuffer.append("(");
            stringBuffer.append("5,");//subjectNo
            stringBuffer.append("'js',");//subjectName
            stringBuffer.append("100,");//classHour
            stringBuffer.append("1");//gradeID
            stringBuffer.append(")");

            System.out.println(stringBuffer);

            //方法1：statement.getUpdateCount()来判断插入的最终结果，如果>0，说明成功，否则失败
//            boolean b = statement.execute(stringBuffer.toString());
            //System.out.println("执行结果："+b+",返回插入的条数="+statement.getUpdateCount());
            //方法2
            int r = statement.executeUpdate(stringBuffer.toString());
            System.out.println("返回插入的条数="+r);



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
//                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }
}
