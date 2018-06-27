package com.lionel.utils.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class JedisUtil {
	private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);
	private static final int DEFAULT_EXPIRE_TIME = 7200;
	private static String address;
	private static String passwd;
	private static ShardedJedisPool shardedJedisPool;
	private static ReentrantLock INSTANCE_INIT_LOCL = new ReentrantLock(false);

	public static void init(String redisUrl) {
		if (isEmpty(address))
			address = redisUrl;
	}

	public static void setPasswd(String passWD) {
		if (isEmpty(passwd))
			passwd = passWD;
	}

	private static ShardedJedis getInstance() {
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

							List jedisShardInfos = new LinkedList();

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

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		if ("".equals(str)) {
			return true;
		}

		return ("null".equalsIgnoreCase(str));
	}

	public static boolean matchWithOutNull(String str1, String str2) {
		return matchWithOutNull(str1, str2, false);
	}

	public static boolean matchWithOutNull(String str1, String str2, boolean ignoreCase) {
		if ((isEmpty(str1)) || (isEmpty(str2))) {
			return false;
		}
		if (ignoreCase) {
			return str1.equalsIgnoreCase(str2);
		}
		return str1.equals(str2);
	}

	private static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error("{}", e);
		} finally {
			try {
				oos.close();
				baos.close();
			} catch (IOException e) {
				logger.error("{}", e);
			}
		}
		return null;
	}

	private static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			logger.error("{}", e);
		} finally {
			try {
				bais.close();
			} catch (IOException e) {
				logger.error("{}", e);
			}
		}
		return null;
	}

	public static String setStringValue(String key, String value, int seconds) {
		String result = null;
		ShardedJedis client = getInstance();
		try {
			result = client.setex(key, seconds, value);
		} catch (Exception e) {
			logger.info("{}", e);
		} finally {
			client.close();
		}
		return result;
	}

	public static String setStringValue(String key, String value) {
		return setStringValue(key, value, 7200);
	}

	public static String setObjectValue(String key, Object obj, int seconds) {
		String result = null;
		ShardedJedis client = getInstance();
		try {
			result = client.setex(key.getBytes(), seconds, serialize(obj));
		} catch (Exception e) {
			logger.info("{}", e);
		} finally {
			client.close();
		}
		return result;
	}

	public static String setObjectValue(String key, Object obj) {
		return setObjectValue(key, obj, 7200);
	}

	public static String getStringValue(String key) {
		String value = null;
		ShardedJedis client = getInstance();
		try {
			value = client.get(key);
		} catch (Exception e) {
			logger.info("", e);
		} finally {
			client.close();
		}
		return value;
	}

	public static Object getObjectValue(String key) {
		Object obj = null;
		ShardedJedis client = getInstance();
		try {
			byte[] bytes = client.get(key.getBytes());
			if ((bytes != null) && (bytes.length > 0))
				obj = unserialize(bytes);
		} catch (Exception e) {
			logger.info("", e);
		} finally {
			client.close();
		}
		return obj;
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

	public static Long incrBy(String key, int i) {
		Long result = null;
		ShardedJedis client = getInstance();
		try {
			result = client.incrBy(key, i);
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

	public static long expire(String key, int seconds) {
		Long result = null;
		ShardedJedis client = getInstance();
		try {
			result = client.expire(key, seconds);
		} catch (Exception e) {
			logger.info("{}", e);
		} finally {
			client.close();
		}
		return result.longValue();
	}

	public static long expireAt(String key, long unixTime) {
		Long result = null;
		ShardedJedis client = getInstance();
		try {
			result = client.expireAt(key, unixTime);
		} catch (Exception e) {
			logger.info("{}", e);
		} finally {
			client.close();
		}
		return result.longValue();
	}
}
