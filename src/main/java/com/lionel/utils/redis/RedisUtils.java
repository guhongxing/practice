package com.lionel.utils.redis;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);
	private static ShardedJedisPool shardedJedisPool;
	private static ReentrantLock INSTANCE_INIT_LOCL = new ReentrantLock(false);
	private static String address="localhost:6379";
	private static String passwd;

	
	
	public static void init(String redisUrl) {
		if (isEmpty(address))
			address = redisUrl;
	}

	public static void setPasswd(String passWD) {
		if (isEmpty(passwd))
			passwd = passWD;
	}
	public static ShardedJedis getInstance() {
		//如果没有初始化，初始化操作
		if (shardedJedisPool == null) {
			try {
				if (INSTANCE_INIT_LOCL.tryLock(2L, TimeUnit.SECONDS))
					try {
						if (shardedJedisPool == null) {
							JedisPoolConfig config = new JedisPoolConfig();
							config.setMaxTotal(200);
							config.setMaxIdle(50);
							config.setMinIdle(8);
							config.setMaxWaitMillis(10000L);
							config.setTestOnBorrow(true);
							config.setTestOnReturn(true);
							config.setTestWhileIdle(true);
							config.setTimeBetweenEvictionRunsMillis(30000L);
							config.setNumTestsPerEvictionRun(10);
							config.setMinEvictableIdleTimeMillis(60000L);

							List<JedisShardInfo> jedisShardInfos = new LinkedList<JedisShardInfo>();

							String[] addressArr = address.split(",");
							for (int i = 0; i < addressArr.length; ++i) {
								String[] addressInfo = addressArr[i].split(":");
								String host = addressInfo[0];
								int port = Integer.valueOf(addressInfo[1]).intValue();
								JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port, 10000);
								jedisShardInfo.setPassword(passwd);
								jedisShardInfos.add(jedisShardInfo);
							}
							shardedJedisPool = new ShardedJedisPool(config, jedisShardInfos);
							logger.info("JedisUtil.ShardedJedisPool init success.");
						}
					} finally {
						INSTANCE_INIT_LOCL.unlock();
					}
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		if (shardedJedisPool == null) {
			throw new NullPointerException("JedisUtil.ShardedJedisPool is null.");
		}
		
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		return shardedJedis;
	}
	
	public static Long del(String key) {
		Long result = null;
		ShardedJedis client = getInstance();
		try {
			result = client.del(key);
		} catch (Exception e) {
			logger.info("{}", e);
		} finally {
			client.close();
		}
		return result;
	}
	
	public static boolean exists(String key) {
		Boolean result = null;
		ShardedJedis client = getInstance();
		try {
			result = client.exists(key);
		} catch (Exception e) {
			logger.info("{}", e);
		} finally {
			client.close();
		}
		return result.booleanValue();
	}
	
	
	/**
	 *@Description ：在redis里设置单个值
	 *@date：2018年5月24日
	 *@author:guhongxing
	 */
	public static String set(String key, String value){
		String result=null;
		ShardedJedis client=getInstance();
		if(client==null){
			return result;
		}
		try {
			result=client.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		
		return result;
	}
	
	/**
	 *@Description ：获取redis单个值
	 *@date：2018年5月24日
	 *@author:guhongxing
	 */
	public static String get(String key) {
        String result = null;
        ShardedJedis client=getInstance();
        if (client == null) {
            return result;
        }
        try {
            result = client.get(key);

        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        } finally {
            client.close();
        }
        return result;
    }
	
	/**
	 *@Description ：获取key存储值类型
	 *@date：2018年5月24日
	 *@author:guhongxing
	 */
	public static String type(String key) {
        String result = null;
        ShardedJedis client=getInstance();
        if (client == null) {
            return result;
        }
        try {
            result = client.type(key);

        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        } finally {
            client.close();
        }
        return result;
    }
	
	/**
	 *@Description ：设置过期时间
	 *@date：2018年5月24日
	 *@author:guhongxing
	 */
	public static Long expire(String key,int seconds) {
		Long result = null;
        ShardedJedis client=getInstance();
        if (client == null) {
            return result;
        }
        try {
            result = client.expire(key, seconds);

        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        } finally {
            client.close();
        }
        return result;
    }
	
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		if ("".equals(str)) {
			return true;
		}

		return ("null".equalsIgnoreCase(str));
	}

}
