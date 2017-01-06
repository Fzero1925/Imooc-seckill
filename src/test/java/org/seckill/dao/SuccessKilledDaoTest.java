package org.seckill.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
	
	@Autowired
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() {
		long id = 1001L;
		long phone = 13512345678L;
		int insertCount = successKilledDao.insertSuccessKilled(id, phone);
		System.out.println("insertCount = " + insertCount);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		long id = 1000L;
		long phone = 13512345678L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckillId());
	}

}
