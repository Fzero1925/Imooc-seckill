package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

/**
 * 
 * @author Fzero
 *
 */
public interface SuccessKilledDao {
	
	/**
	 * 插入购买明细，可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(long seckillId, long userPhone);
	
	/**
	 * 根据id查询SuccessKilled并携带Seckill实体
	 * @param seckill
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(long seckill);

}



















