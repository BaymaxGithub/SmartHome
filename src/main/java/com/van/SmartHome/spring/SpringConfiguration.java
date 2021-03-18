package com.van.SmartHome.spring;


import com.fasterxml.jackson.databind.Module;
import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.van.SmartHome.config.Config;
import com.van.SmartHome.jackson.CloudJacksonModule;
import com.van.SmartHome.mongodb.MongoFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;


/**
 *
 *
 */

//@Configuration的注解类标识这个类可以使用Spring IoC容器作为bean定义的来源
//定义一个注解配置文件 必须要加上@Configuration注解
@Configuration
@ComponentScan("com.van.SmartHome")
@Slf4j
@PropertySource("classpath:mongo.properties")
//@ComponentScan：会自动扫描指定包下的全部标有@Component的类，并注册成bean，当然包括@Component下的子注解@Service,@Repository,@Controller。
public class SpringConfiguration {

    @Value("${config.mongo.host}")
    String host;

    @Value("${config.mongo.port}")
    int port;

    @Value("${config.mongo.db}")
    String dbName;

    @Value("${config.mongo.table}")
    String collection;

    @Value("${config.mongo.username}")
    String username;

    @Value("${config.mongo.password}")
    String password;

    @Value("${config.mongo.name}")
    String name;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        MongoCredential credential = MongoCredential.createCredential(username, dbName, password.toCharArray());
        ArrayList<MongoCredential> credentials = Lists.newArrayList(credential);
        ServerAddress address = new ServerAddress(host, port);
        MongoClient mongo = new MongoClient(address, credentials);
        return new SimpleMongoDbFactory(mongo,  dbName);
    }
    @Bean
    public Config config(MongoDbFactory mongoDbFactory) throws IOException {
        MongoTemplate template = new MongoTemplate(mongoDbFactory);
        log.info("init config bean of {} from {}", name, collection);
        return new Config(template, collection, name);
    }

    @Bean
    public MongoClient mongoClient(Config config) throws UnknownHostException {
        return config.createMongoClient();
    }

    @Bean
    public Mongo mongo(MongoClient client) {
        return client;
    }

    @Bean
    //@Bean是一个方法级别上的注解，主要用在@Configuration注解的类里，也可以用在@Component注解的类里。添加的bean的id为方法名@Bean(name="BeanTest")
    //Spring的@Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。产生这个Bean对象的方法Spring只会调用一次
    // ，随后这个Spring将会将这个Bean对象放在自己的IOC容器中。
    public MongoFactory mongoFactory(MongoClient mongo) {
        return new MongoFactory(mongo);
    }


    //解决ObjectId变成分解形式，转化成字符串
    @Bean
    public Module elementsJacksonModule() {
        return new CloudJacksonModule();
    }


    @Lazy
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

    @Lazy
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        return executor;
    }
}