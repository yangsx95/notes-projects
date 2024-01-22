package me.feathers.demo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * PrimaryDao
 * <p>
 *
 * @author Feathers
 * @date 2018-06-01 14:16
 */
@Repository
public class PrimaryDao {

    @Resource(name = "primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    public String queryUsers() {
        List<Map<String, Object>> maps = primaryJdbcTemplate.queryForList("SELECT * FROM TEST_USER");
        return maps.toString();
    }

}
