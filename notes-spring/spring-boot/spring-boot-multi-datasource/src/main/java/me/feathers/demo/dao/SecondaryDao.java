package me.feathers.demo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * SecondaryDao
 * <p>
 *
 * @author Feathers
 * @date 2018-06-01 14:21
 */
@Repository
public class SecondaryDao {

    @Resource(name = "secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    public String queryUsers() {
        List<Map<String, Object>> maps = secondaryJdbcTemplate.queryForList("SELECT * FROM TEST_USER");
        return maps.toString();
    }

}
