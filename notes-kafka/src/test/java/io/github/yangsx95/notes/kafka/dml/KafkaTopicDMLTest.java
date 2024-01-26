package io.github.yangsx95.notes.kafka.dml;

import org.apache.kafka.clients.admin.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class KafkaTopicDMLTest {

    /**
     * 用于Kafka服务管理
     */
    private AdminClient kafkaAdminClient;

    /**
     * 创建Kafka管理客户端
     */
    @Before
    public void before() {
        Properties prop = new Properties();
        prop.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "s2.svc.com:9092,s3.svc.com:9092,s4.svc.com:9092");
        kafkaAdminClient = KafkaAdminClient.create(prop);
    }

    /**
     * 执行完毕后，关闭客户端
     */
    @After
    public void after() {
        kafkaAdminClient.close();
    }

    /**
     * 列出topic列表
     */
    @Test
    public void listTopics() throws Exception {
        ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
        Set<String> names = listTopicsResult.names().get();
        System.out.println(names);
    }

    /**
     * 创建topic
     */
    @Test
    public void createTopic() throws Exception {
        // KafkaClient API中， 所有操作都是异步操作
        CreateTopicsResult createTopicsResult = kafkaAdminClient.createTopics(
                // Topic名称， 分区数， 副本因子
                Collections.singletonList(new NewTopic("Topic02", 3, (short) 3)));

        ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
        Set<String> names = listTopicsResult.names().get(); // 获取执行结构
        System.out.println(names);

        createTopicsResult.all().get(); // all方法将会返回一个Future接口，异步执行完毕后，才会得到结果， 所以上方的代码查询不到Topic02

        ListTopicsResult listTopicsResult1 = kafkaAdminClient.listTopics();
        Set<String> names1 = listTopicsResult1.names().get(); // 获取执行结构
        System.out.println(names1);
    }

    /**
     * 查看Topic详细信息
     */
    @Test
    public void describeTopic() throws ExecutionException, InterruptedException {
        DescribeTopicsResult topic02 = kafkaAdminClient.describeTopics(Collections.singletonList("Topic02"));
        Map<String, TopicDescription> res = topic02.all().get();
        System.out.println("---------");
        res.forEach((k, v) -> System.out.println(k + ": " + v.toString()));
        System.out.println("---------");
    }

    /**
     * 删除Topic
     */
    @Test
    public void deleteTopic() throws Exception {
        DeleteTopicsResult topic02 = kafkaAdminClient.deleteTopics(Collections.singletonList("Topic02"));
        topic02.all().get(); // 同步执行， 立即获取执行结果

        ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
        Set<String> names = listTopicsResult.names().get(); // 获取执行结构
        System.out.println(names);
    }


}