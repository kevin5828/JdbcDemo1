package DaoDemo;

import java.sql.ResultSet;
import java.util.List;

public interface UserinfoDao {
    public boolean save(Userinfo userinfo);
    public boolean del();
    public boolean update();
    public List<Userinfo> findAll();

}
