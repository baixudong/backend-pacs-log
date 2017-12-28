package com.radida.pacs.redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Test {
	@Autowired
	protected  JedisPool queueWriteRedisPool;
	
	public static void main(String[] args) {
		Jedis writeRedisClient = (Jedis) RedisUtil.getJedis();
		writeRedisClient.del("80358558e7f14cb88428b9efcad4fed2:f5b54bb6174b4cda8599ad771a24e5e0");
		writeRedisClient.zadd("80358558e7f14cb88428b9efcad4fed2:f5b54bb6174b4cda8599ad771a24e5e0", 2, "86412dfbae7a4359834a5a80c1046ce0:PM002");
		writeRedisClient.zadd("80358558e7f14cb88428b9efcad4fed2:f5b54bb6174b4cda8599ad771a24e5e0", 3, "f2aa57d04338433cbdada453d797d117:PM003");
		writeRedisClient.zadd("80358558e7f14cb88428b9efcad4fed2:f5b54bb6174b4cda8599ad771a24e5e0", 4, "1ee82835423542cf9e317de28c213323:PM004");
		System.out.println(writeRedisClient.zrank("80358558e7f14cb88428b9efcad4fed2:f5b54bb6174b4cda8599ad771a24e5e0", "1ee82835423542cf9e317de28c213323:PM004"));
	}

}
