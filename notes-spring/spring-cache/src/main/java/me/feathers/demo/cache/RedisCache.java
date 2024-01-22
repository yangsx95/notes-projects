package me.feathers.demo.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.util.concurrent.Callable;

public class RedisCache implements Cache {

    private RedisTemplate<String, Object> redisTemplate;
    private String name;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 设置缓存名称
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 存入一个缓存
     */
    @Override
    public void put(Object key, Object value) {
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400; // 设置有效期
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializationUtils.serialize(valuef);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * 如果不存在就存入一个缓存
     *
     * @param key   key
     * @param value value
     */
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (get(key) != null) {
            this.put(key, value);
        }
        return null;
    }

    /**
     * 根据key获取缓存内容
     *
     * @param key key
     * @return 缓存内容
     */
    @Override
    public ValueWrapper get(Object key) {
        final String keyf = key.toString();
        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    System.out.println("------缓存不存在-------");
                    return null;
                }
                return SerializationUtils.deserialize(value);
            }
        });
        ValueWrapper obj = (object != null ? new SimpleValueWrapper(object) : null);
        System.out.println("------获取到内容-------" + obj);
        return obj;
    }

    /**
     * 根据key和类型获取缓存内容
     */
    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    /**
     * 根据key获取缓存内容，并执行回调 callable
     */
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    /**
     * 删除指定的缓存
     *
     * @param key key
     */
    @Override
    public void evict(Object key) {
        final String keyf = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
        });
    }

    /**
     * 获取底层的缓存提供者
     */
    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    /**
     * 清空缓存
     */
    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }


}
