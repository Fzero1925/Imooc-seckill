package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.seckill.entity.Seckill;

/**
 * 
 * @author Fzero
 *
 */
public interface SeckillDao {
	
	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return 返回受影响的行数
	 */
	int reduceNumber(long seckillId, Date killTime);
	
	/**
	 * 根据id查询秒杀对象
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(int offset, int limit);

}





















