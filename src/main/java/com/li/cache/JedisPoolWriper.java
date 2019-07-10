package com.li.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: JedisPoolWriper
 * @Description:
 * @author: libl
 * @date: 2019/07/08 14:21
 */
public class JedisPoolWriper {
    private JedisPool jedisPool;

    public JedisPoolWriper(final JedisPoolConfig poolConfig,final String host,final int port){
        try {
            jedisPool=new JedisPool(poolConfig,host,port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JedisPool getJedisPool(){
        return jedisPool;
    }
    public void setJedisPool(JedisPool jedisPool){
        this.jedisPool=jedisPool;
    }
}
