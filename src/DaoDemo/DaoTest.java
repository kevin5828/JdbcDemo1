package DaoDemo;

import DaoDemo.DaoImpl.UserinfoDaoImpl;

import java.util.List;

public class DaoTest {
    public static void main(String[] args) {
        UserinfoDao uid = new UserinfoDaoImpl();
/*        //模拟页面数据
        Userinfo userinfo = new Userinfo();
        userinfo.setCustomerID(6);
        userinfo.setCustomerName("布丁");
        userinfo.setPID("321234567890");
        userinfo.setTelephone("1860000001");
        userinfo.setAddress("南京湖南路");
        //调用保存数据库方法
        boolean r = uid.save(userinfo);
        if(r){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }*/

        List<Userinfo> list = uid.findAll();
        System.out.println("查询到总记录数："+list.size());
//        Userinfo userinfo = list.get(0);
//        System.out.println("CustomerID="+userinfo.getCustomerID()+",CustomerName="+userinfo.getCustomerName());
        for (Userinfo u: list) {
            System.out.println(u.getCustomerID()+","+
                    u.getCustomerName()+","+
                    u.getPID()+","+
                    u.getTelephone()+","+
                    u.getAddress());
        }
    }
}
