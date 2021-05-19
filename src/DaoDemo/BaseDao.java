package DaoDemo;

import java.sql.*;

public class BaseDao {
    static public Connection connection = null;
    static public PreparedStatement pstm = null;
    static public ResultSet resultSet = null;
    static {
        getConnDef();
    }

    /**
     * 创建连接
     * @return
     */
    static public Connection getConnDef(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","123456");
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    /**
     * 关闭所有资源
     */
    public void closeResource(){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(pstm != null) {
                pstm.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 通用的执行方法
     * @param pSql
     * @param params
     * @return
     */
    public boolean executeSql(String pSql, Object[] params) {
        int r = 0;
        try {
            pstm = connection.prepareStatement(pSql);
            for (int i=1;(params != null && i<=params.length);i++){
                pstm.setObject(i,params[i-1]);
            }
            r = pstm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeResource();
        }
        return r>0?true:false;
    }

    /**
     * 通用的查询数据库的表的方法
     * @param sql
     * @return
     * @throws SQLException
     */
    public ResultSet excuteQuery(String sql) {
        try {
            pstm = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        try {
            return pstm.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
