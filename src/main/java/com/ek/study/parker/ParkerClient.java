package com.ek.study.parker;

import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
public class ParkerClient {


    public static void main(String[] args){
        ParkerClient.init();
    }
    private static void init(){
        HostAndPort host1 = new HostAndPort("10.177.200.179", 30003);
        HostAndPort host2 = new HostAndPort("10.177.200.180", 30003);
        Set<HostAndPort> hosts = new HashSet<>();
        hosts.add(host1);
        hosts.add(host2);

        JedisCluster jedisCluster = new JedisCluster(hosts);
        jedisCluster.set("parker", "my name is parker");
        System.out.println("set success");
        String parker = jedisCluster.get("parker");
        System.out.println(parker);


    }


}
