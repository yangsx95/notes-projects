package me.feathers.ssm;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * AbsBaseTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 11:16
 */
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbsBaseTest {
}
