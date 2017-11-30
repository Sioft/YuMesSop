package com.sop.test;

import org.apache.ibatis.session.SqlSession;
import com.sop.entity.ProcessDesignInfo;
import com.sop.util.MyBatisUtil;

public class MyBatisTest {
	//private static SqlSessionFactory sqlSessionFactory;
	//private static Reader reader;
	/*static {
		
		try {
			//
			
			//
			String resource = "SqlMapConfig.xml";
			//使用MyBatis 提供的Resource类型加载mybatis的配置文件（它也加载关联的映射文件）
			reader = Resources.getResourceAsReader(resource);
			//构建sqlSession的工厂
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public static void main(String[] args){
		SqlSession session = MyBatisUtil.getSession();
		//SqlSession session = sqlSessionFactory.openSession();
		//插入测试
		ProcessDesignInfo a = new ProcessDesignInfo();
		//a.setPdgCode("s003");
		//a.setPdgId(12);
		int flag = session.insert("ProcessDesignDao.save", a);
		session.commit();
		System.out.print(flag);
		MyBatisUtil.closeSession();
		//ProcessDesignInfo info = (ProcessDesignInfo)session.selectOne("ProcessDesignDao.findAll");
		//System.out.print(info.toString());	
			//最基本的mybatis 测试
			//User user = (User)session.select("");
			/*InputStream is=Resources.getResourceAsStream("mybatis-config.xml");<!--加载核心配置文件 -->
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(is);《！--  获取session工厂类--》
            SqlSession session=sessionFactory.openSession();《！—— 获取sqlsession对象 ——》
            SystemNews sNews=new SystemNews();《！-- 实例化实体类 ---》
            sNews.setContent("mybatis");
            sNews.setNic("mybatis");
            sNews.setTime(new Date());
            session.insert("org.mybatis.entitys.SystemNews.add",sNews);《！--绿色部分是sql映射文件中的package的值 add是对于的inset标签的id的值---》
            session.commit();《！——ok提交完毕！ 数据库中就新增一条记录————》*/
   
	
	}
}
