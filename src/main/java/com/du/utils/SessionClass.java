package com.du.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionClass {

    public static InputStream inputStream(){
        InputStream in = null;
        return in;
    }

    public static SqlSession returnSqlSession(){
        //1. 读取配置文件
        try {
            InputStream in = inputStream();
             in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2. 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream());
        //3. 使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        return session;
    }


}
