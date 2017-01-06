package org.seckill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置Spring和junit整合
 * @author Fzero
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
	
	//注入DAO实现类依赖
	@Autowired
	private SeckillDao seckillDao;	

	@Test
	public void testReduceNumber() throws Exception {
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumber(1000L, killTime);
		System.out.println("updateCount = " + updateCount);
	}

	@Test
	public void testQueryById() throws Exception {
		long id = 1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public void testQueryAll() throws Exception {
		List<Seckill> seckills = seckillDao.queryAll(0, 100);
		for(Seckill seckill : seckills){
			System.out.println(seckill);
			System.out.println();
		}
	}

}











