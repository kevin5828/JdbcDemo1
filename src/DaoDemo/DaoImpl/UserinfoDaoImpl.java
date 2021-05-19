package DaoDemo.DaoImpl;

import DaoDemo.BaseDao;
import DaoDemo.Userinfo;
import DaoDemo.UserinfoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserinfoDaoImpl extends BaseDao implements UserinfoDao {
    /**
     * 插入单条userinfo数据
     * @param userinfo
     * @return 如果执行成功返回true，否则返回false
     */
    @Override
    public boolean save(Userinfo userinfo) {
        //insert语句
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("INSERT into userinfo(customerId,customerName,pid,telephone,address) ");
        stringBuffer.append("values (?,?,?,?,?)");
        Object[] params = new Object[5];
        params[0] = userinfo.getCustomerID();
        params[1] = userinfo.getCustomerName();
        params[2] = userinfo.getPID();
        params[3] = userinfo.getTelephone();
        params[4] = userinfo.getAddress();

        System.out.println(stringBuffer);

        boolean b = executeSql(stringBuffer.toString(),params);

        return b;

    }



    @Override
    public boolean del() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }



    public ResultSet findAllIni() {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","123456");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
            String sql = "select customerID,customerName,pid,telephone,address from userinfo ";
            pstm = connection.prepareStatement(sql);

            resultSet = pstm.executeQuery();
            int i = 1;
            int customerID = 0;
            String customerName;
//            while (i < 2){
            while (resultSet.next()){
                customerID = resultSet.getInt("customerID");
//                resultSet.get("customerID")
                customerName = resultSet.getString("customerName");
                System.out.println(""+customerID+","+customerName);
//                i++;
            }
        } catch (SQLException throwables) {
            System.out.println("执行了catch方法！");
            throwables.printStackTrace();
        } finally {
            System.out.println("执行了finally方法！");
            try {
                resultSet.close();
                pstm.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return resultSet;
    }
    @Override
    public List<Userinfo> findAll() {
        List list = new ArrayList();
        Userinfo userinfo = null;
        try {
            String sql = "select customerID,customerName,pid,telephone,address from userinfo ";
            resultSet = excuteQuery(sql);
            while (resultSet.next()){
                userinfo = new Userinfo();
                userinfo.setCustomerID(resultSet.getInt("customerID"));
                userinfo.setCustomerName(resultSet.getString("customerName"));
                userinfo.setPID(resultSet.getString("pid"));
                userinfo.setTelephone(resultSet.getString("telephone"));
                userinfo.setAddress(resultSet.getString("address"));
                list.add(userinfo);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResource();
        }
        return list;
    }



}
