package org.oursight.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by neyao@github.com on 2016/4/10.
 */
public class RedisClientDemo {

    private static Jedis jedis;

    public static void main(String[] args) {
        jedis = new Jedis("localhost");
//         jedis = new Jedis("192.168.0.1");
        ping();
        setAndGet();
        list();
        getAllKeys();
    }

    public static void ping() {
        System.out.println("connect sucessfully");
        System.out.println("jedis.ping() = " + jedis.ping());
    }

    public static void setAndGet() {
        jedis.set("mykey1", "123456");
        jedis.get("mykey1");
    }

    public static void list() {
        jedis.lpush("mykey-list", "111");
        jedis.lpush("mykey-list", "222");
        jedis.lpush("mykey-list", "333");

        List<String> list = jedis.lrange("mykey-list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
    }

    public static void getAllKeys() {
        Set<String> keys = jedis.keys("*");

        System.out.println("-------- All keys --------");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key  = it.next();
            //System.out.println(key +": " + jedis.get(key));

            System.out.println(key + "; type of this key: " + jedis.type(key));
        }

        System.out.println("------------------------");


    }
}
