import com.du.dao.UserDao;
import com.du.pojo.User;
import com.du.vo.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
  Mybatis的入门案例
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private UserDao userDao;

    @Before  //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1. 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2. 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3. 使用工厂生产SqlSession对象
        session = factory.openSession();
        //4. 使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(UserDao.class);
    }

    @After  //用于在测试方法执行之后执行
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void findAll() {

        //5. 使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }

    }

    //根据名称模糊查询用户信息
    @Test
    public void findByName(){
        List<User> users = userDao.findByName("h");
        for(User user : users){
            System.out.println(user);
        }
    }

    //根据名称模糊查询用户信息
    @Test
    public void findByNameVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%h%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for(User u : users){
            System.out.println(u);
        }
    }

    @Test
    public void testFindUserByCondition() {

        User user = new User();
        user.setUsername("hhhhh");
        user.setSex("男");
        //5. 使用代理对象执行方法
        List<User> users = userDao.findUserByCondition(user);
        for(User u : users){
            System.out.println(u);
        }

    }

    //测试foreach标签的使用
    @Test
    public void findUserInIds() {

        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        list.add(4);
        vo.setIds(list);
        //5. 使用代理对象执行方法
        List<User> users = userDao.findUserInIds(vo);
        for(User u : users){
            System.out.println(u);
        }

    }



}
