package com.example.demo.config;

import com.example.demo.store.CustomerMapstore;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public Config makeCustomConfig(CustomerMapstore customerMapstore) {
        Config config = new Config();
        //1. Set port number
        //2. Auto increment port number
        //3. Disable multi-cast
        //4. Enable TCP/IP in localhost
        //5. Setup mapstore to config
        NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.setPort(5701);
        networkConfig.setPortAutoIncrement(true);
        JoinConfig joinConfig = networkConfig.getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().addMember("localhost").setEnabled(true);
        MapConfig mapConfig = new MapConfig();
        MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setImplementation(customerMapstore);
        mapConfig.setMapStoreConfig(mapStoreConfig);
        mapConfig.setName("customer");
        config.addMapConfig(mapConfig);
        return config;
    }

    @Bean
    public HazelcastInstance getHzcServerInstance(Config customConfig) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(customConfig);
        return hazelcastInstance;
    }

}
