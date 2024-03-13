package io.github.yangsx95.notes.shardingsphere;

import io.github.yangsx95.notes.shardingsphere.entity.Contract;
import io.github.yangsx95.notes.shardingsphere.entity.ContractSection;
import io.github.yangsx95.notes.shardingsphere.entity.ContractVo;
import io.github.yangsx95.notes.shardingsphere.mapper.ContractMapper;
import io.github.yangsx95.notes.shardingsphere.mapper.ContractSectionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.List;

/**
 * 水平分表测试
 *
 * @author yangshunxiang
 * @since 2024/3/12
 */
@SpringBootTest
@ActiveProfiles("绑定表")
public class 绑定表Test {

    @Resource
    private ContractMapper contractMapper;

    @Resource
    private ContractSectionMapper contractSectionMapper;

    @Test
    public void insert() {
        for (int i = 0; i < 3; i++) {
            Contract contract = new Contract();
            //userID 为分库策略的分片键
            contract.setUserId(1L + i);
            contract.setCname("Java面试题详解");
            contract.setCorderNo(1000L + i);
            contract.setBrief("经典的10000道面试题");
            contract.setPrice(100.00);
            contract.setStatus(1);
            contractMapper.insert(contract);

            Long cid = contract.getCid();
            for (int j = 0; j < 3; j++) {
                ContractSection section = new ContractSection();
                section.setCid(cid);
                section.setUserId(1L + i);
                //corderNo是分表策略的分片键
                section.setCorderNo(1000L + i);
                section.setSectionName("Java面试题详解_" + i);
                section.setStatus(1);
                contractSectionMapper.insert(section);
            }
        }

    }

    //关联查询不会再产生笛卡尔积
    @Test
    public void testSelectCourseNameAndSectionNum() {
        List<ContractVo> list = contractSectionMapper.getCourseNameAndSectionNum();
        list.forEach(System.out::println);
    }

}
