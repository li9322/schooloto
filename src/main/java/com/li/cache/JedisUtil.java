package com.li.cache;


import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import javax.sql.rowset.serial.SerialStruct;

/**
 * @ClassName: JedisUtil
 * @Description:
 * @author: libl
 * @date: 2019/07/08 14:26
 */
public class JedisUtil {

    private final int expire=60000;

    public Keys KEYS;

    public Strings STRINGS;

    public Lists LISTS;

    public Sets SETS;

    public Hash HASH;

    private JedisPool jedisPool;

    public JedisPool getJedisPool(){
        return jedisPool;
    }

    public void setJedisPool(JedisPoolWriper jedisPoolWriper){
        this.jedisPool=jedisPoolWriper.getJedisPool();
    }

    public JedisPool getPool(){
        return jedisPool;
    }

    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    public void expire(String key,int seconds){
        if (seconds<=0){
            return;
        }
        Jedis jedis=getJedis();
        jedis.expire(key,seconds);
        jedis.close();
    }

    public void expire(String key){
        expire(key,expire);
    }

    public class Keys{

        public String flushAll(){
            Jedis jedis=getJedis();
            String stata=jedis.flushAll();
            jedis.close();
            return stata;
        }

        public String rename(String oldKey,String newkey){
            return rename(SafeEncoder.encode(oldKey),SafeEncoder.encode(newkey));
        }

        public long renamenx(String oldKey,String newkey){
            Jedis jedis=getJedis();
            long status=jedis.renamenx(oldKey,newkey);
            jedis.close();
            return status;
        }

        public String rename(byte[] oldKey,byte[] newkey){
            Jedis jedis=getJedis();
            String status=jedis.rename(oldKey,newkey);
            jedis.close();
            return status;
        }

        public long expired(String key,int seconds){
            Jedis jedis=getJedis();
            long count=jedis.expire(key,seconds);
            jedis.close();
            return count;
        }

        public long expirAt(String key,long timestamp){
            Jedis jedis=getJedis();
            long count=jedis.expireAt(key,timestamp);
            jedis.close();
            return count;
        }

        public long ttl(String key){
            Jedis sjedis=getJedis();
            long len=sjedis.ttl(key);
            sjedis.close();
            return len;
        }

        public long persist(String key){
            Jedis jedis=getJedis();
            long count=jedis.persist(key);
            jedis.close();
            return count;
        }

        public long del(String... keys){
            Jedis jedis=getJedis();
            long count=jedis.del(keys);
            jedis.close();
            return count;
        }

        public long del(byte[]... keys){
            Jedis jedis=getJedis();
            long count=jedis.del(keys);
            jedis.close();
            return count;
        }

        public boolean exists(String key){
            Jedis sjedis=getJedis();
            boolean exist=sjedis.exists(key);
            sjedis.close();
            return exist;
        }

        public List<String> sort(String key){
            Jedis sjedis=getJedis();
            List<String> list=sjedis.sort(key);
            sjedis.close();
            return list;
        }

        public List<String> sort(String key,SortingParams params){
            Jedis sjedis=getJedis();
            List<String> list=sjedis.sort(key,params);
            sjedis.close();
            return list;
        }

        public String type(String key){
            Jedis sjedis=getJedis();
            String type=sjedis.type(key);
            sjedis.close();
            return type;
        }

        public Set<String> keys(String pattern){
            Jedis sjedis=getJedis();
            Set<String> set=sjedis.keys(pattern);
            sjedis.close();
            return set;
        }
    }

    public class Sets{

        public long sadd(String key,String member){
            Jedis jedis=getJedis();
            long s=jedis.sadd(key,member);
            jedis.close();
            return s;
        }

        public long sadd(byte[] key,byte[] member){
            Jedis jedis=getJedis();
            long s=jedis.sadd(key,member);
            jedis.close();
            return s;
        }

        public long scard(String key){
            Jedis jedis=getJedis();
            long len=jedis.scard(key);
            jedis.close();
            return len;
        }

        public Set<String> sdiff(String... key){
            Jedis jedis=getJedis();
            Set<String> set=jedis.sdiff(key);
            jedis.close();
            return set;
        }

        public long sdiffstore(String newkey,String... keys){
            Jedis jedis=getJedis();
            long s=jedis.sdiffstore(newkey,keys);
            jedis.close();
            return s;
        }

        public Set<String> sinter(String... keys){
            Jedis jedis=getJedis();
            Set<String> set=jedis.sdiff(keys);
            jedis.close();
            return set;
        }

        public long sinterstore(String newkey,String... keys){
            Jedis jedis=getJedis();
            long s=jedis.sdiffstore(newkey,keys);
            jedis.close();
            return s;
        }

        public boolean sismember(String key,String member){
            Jedis jedis=getJedis();
            boolean s=jedis.sismember(key,member);
            jedis.close();
            return s;
        }

        public Set<String> smembers(String key){
            Jedis jedis=getJedis();
            Set<String> set=jedis.smembers(key);
            jedis.close();
            return set;
        }

        public Set<byte[]> smembers(byte[] key){
            Jedis jedis=getJedis();
            Set<byte[]> set=jedis.smembers(key);
            jedis.close();
            return set;
        }

        public long smove(String srckey,String dstkey,String member){
            Jedis jedis=getJedis();
            long s=jedis.smove(srckey,dstkey,member);
            jedis.close();
            return s;
        }

        public String spop(String key){
            Jedis jedis=getJedis();
            String s=jedis.spop(key);
            jedis.close();
            return s;
        }

        public long srem(String key,String member){
            Jedis jedis=getJedis();
            long s=jedis.srem(key,member);
            jedis.close();
            return s;
        }

        public Set<String> sunion(String... keys){
            Jedis jedis=getJedis();
            Set<String> set=jedis.sunion(keys);
            jedis.close();
            return set;
        }

        public long sunionstore(String newkey,String... keys){
            Jedis jedis=getJedis();
            long s=jedis.sunionstore(newkey,keys);
            jedis.close();
            return s;
        }
    }
    public class Hash{

        public long hdel(String key,String fieid){
            Jedis jedis=getJedis();
            long s=jedis.hdel(key,fieid);
            jedis.close();
            return s;
        }

        public long hdel(String key){
            Jedis jedis=getJedis();
            long s=jedis.hdel(key);
            jedis.close();
            return s;
        }

        public boolean hexists(String key,String fieid){
            Jedis jedis=getJedis();
            boolean s=jedis.hexists(key,fieid);
            jedis.close();
            return s;
        }

        public String hget(String key,String fieid){
            Jedis jedis=getJedis();
            String s=jedis.hget(key,fieid);
            jedis.close();
            return s;
        }

        public byte[] hget(byte[] key,byte[] fieid){
            Jedis jedis=getJedis();
            byte[] s=jedis.hget(key,fieid);
            jedis.close();
            return s;
        }

        public Map<String,String> hgetAll(String key){
            Jedis jedis=getJedis();
            Map<String,String> map=jedis.hgetAll(key);
            jedis.close();
            return map;
        }

        public long hset(String key,String fieid,String value){
            Jedis jedis=getJedis();
            long s=jedis.hset(key,fieid,value);
            jedis.close();
            return s;
        }

        public long hset(String key,String fieid,byte[] value){
            Jedis jedis=getJedis();
            long s=jedis.hset(key.getBytes(),fieid.getBytes(),value);
            jedis.close();
            return s;
        }

        public long hsetnx(String key,String fieid,String value){
            Jedis jedis=getJedis();
            long s=jedis.hsetnx(key,fieid,value);
            jedis.close();
            return s;
        }

        public List<String> hvals(String key){
            Jedis jedis=getJedis();
            List<String> list=jedis.hvals(key);
            jedis.close();
            return list;
        }

        public long hincrBy(String key,String fieid,long value){
            Jedis jedis=getJedis();
            long s=jedis.hincrBy(key,fieid,value);
            jedis.close();
            return s;
        }

        public Set<String> hkeys(String key){
            Jedis jedis=getJedis();
            Set<String> set=jedis.hkeys(key);
            jedis.close();
            return set;
        }

        public long hlen(String key){
            Jedis jedis=getJedis();
            long len=jedis.hlen(key);
            jedis.close();
            return len;
        }

        public List<String> hmget(String key,String... fieids){
            Jedis jedis=getJedis();
            List<String> list=jedis.hmget(key,fieids);
            jedis.close();
            return list;
        }

        public List<byte[]> hmget(byte[] key,byte[]... fieids){
            Jedis jedis=getJedis();
            List<byte[]> list=jedis.hmget(key,fieids);
            jedis.close();
            return list;
        }

        public String hmset(String key,Map<String,String> map){
            Jedis jedis=getJedis();
            String s=jedis.hmset(key,map);
            jedis.close();
            return s;
        }

        public String hmget(byte[] key,Map<byte[],byte[]> map){
            Jedis jedis=getJedis();
            String s=jedis.hmset(key,map);
            jedis.close();
            return s;
        }
    }
    public class Strings{

        public String get(String key){
            Jedis jedis=getJedis();
            String value=jedis.get(key);
            jedis.close();
            return value;
        }

        public byte[] get(byte[] key){
            Jedis jedis=getJedis();
            byte[] value=jedis.get(key);
            jedis.close();
            return value;
        }

        public String setEx(String key,int seconds,String value){
            Jedis jedis=getJedis();
            String str=jedis.setex(key,seconds,value);
            jedis.close();
            return str;
        }

        public String setEx(byte[] key,int seconds,byte[] value){
            Jedis jedis=getJedis();
            String str=jedis.setex(key,seconds,value);
            jedis.close();
            return str;
        }

        public long setnx(String key,String value){
            Jedis jedis=getJedis();
            long str=jedis.setnx(key,value);
            jedis.close();
            return str;
        }

        public String set(String key,String value){
            return set(SafeEncoder.encode(key),SafeEncoder.encode(value));
        }

        public String set(String key,byte[] value){
            return set(SafeEncoder.encode(key),value);
        }

        public String set(byte[] key,byte[] value){
            Jedis jedis=getJedis();
            String status=jedis.set(key,value);
            jedis.close();
            return status;
        }

        public long setRange(String key,long offset,String value){
            Jedis jedis=getJedis();
            long len=jedis.setrange(key,offset,value);
            jedis.close();
            return len;
        }

        public long append(String key,String value){
            Jedis jedis=getJedis();
            long len=jedis.append(key,value);
            jedis.close();
            return len;
        }

        public long decrBy(String key,long number){
            Jedis jedis=getJedis();
            long len=jedis.decrBy(key,number);
            jedis.close();
            return len;
        }

        public long incrBy(String key,long number){
            Jedis jedis=getJedis();
            long len=jedis.incrBy(key,number);
            jedis.close();
            return len;
        }

        public String getRange(String key,long startOffset,long endOffset){
            Jedis jedis=getJedis();
            String value=jedis.getrange(key,startOffset,endOffset);
            jedis.close();
            return value;
        }

        public String getSet(String key,String value){
            Jedis jedis=getJedis();
            String str=jedis.getSet(key,value);
            jedis.close();
            return str;
        }

        public List<String> mget(String... keys){
            Jedis jedis=getJedis();
            List<String> list=jedis.mget(keys);
            jedis.close();
            return list;
        }

        public String mset(String... keysvalues){
            Jedis jedis=getJedis();
            String str=jedis.mset(keysvalues);
            jedis.close();
            return str;
        }

        public long strlen(String key){
            Jedis jedis=getJedis();
            long len=jedis.strlen(key);
            jedis.close();
            return len;
        }

    }
    public class Lists{
        public long llen(String key){
            return 0;
        }
    }
}
