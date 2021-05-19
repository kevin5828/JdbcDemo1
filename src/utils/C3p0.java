package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * c3p0连接池
 *
 * @author Administrator
 */
public class C3p0 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet resultSet = null;
        //创建一个c3p0数据源对象
        ComboPooledDataSource scoure = new ComboPooledDataSource();
        //利用对象读取配置信息
        //注释掉这段代码后会去找配置文件，存在任意一个都可以成功
        try {
            scoure.setDriverClass("com.mysql.jdbc.Driver");
            scoure.setJdbcUrl("jdbc:mysql://localhost:3306/bankdb");
            scoure.setUser("root");
            scoure.setPassword("123456");
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            conn = scoure.getConnection();
            stat = conn.createStatement();
            int customerID = 0;
            String customerName;
            resultSet = stat.executeQuery("select customerID,customerName,pid,telephone,address from userinfo");
            while (resultSet.next()) {
                customerID = resultSet.getInt("customerID");
//                resultSet.get("customerID")
                customerName = resultSet.getString("customerName");
                System.out.println("" + customerID + "," + customerName);
//                i++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    resultSet = null;
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    stat = null;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    conn = null;
                }
            }
        }

    }
}
