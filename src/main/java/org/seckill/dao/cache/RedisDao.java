package org.seckill.dao.cache;

import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final JedisPool jedisPool;
	
	public RedisDao(String ip, int port){
		jedisPool = new JedisPool(ip, port);
	}
	
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
	
	/**
	 * 从redis获取数据，由于在redis中的数据都是字节数组，所以需要对数据进行反序列化，转化为想要的类型对象
	 * @param seckillId
	 * @return
	 */
	public Seckill getSeckill(long seckillId){
		//redis操作逻辑
		try{
			Jedis jedis = jedisPool.getResource();
			try{
				String key = "seckill: " + seckillId;
				//Redis没有实现内部序列化操作，采用自定义序列化
				byte[] bytes = jedis.get(key.getBytes());	
				if(bytes != null){
					//空对象
					Seckill seckill = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					//seckill被反序列
					return seckill;
				}
			} finally {
				jedis.close();
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 把数据放入redis中时，需要对数据进行序列化，转化为字节数组
	 * @param seckill
	 * @return
	 */
	public String putSeckill(Seckill seckill){
		try{
			Jedis jedis = jedisPool.getResource();
			try{
				String key = "seckill: " + seckill.getSeckillId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				
				//超时缓存
				int timeout = 60 * 60;//一小时
				String result = jedis.setex(key.getBytes(), timeout, bytes);
				return result;
			} finally {
				jedis.close();
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}














