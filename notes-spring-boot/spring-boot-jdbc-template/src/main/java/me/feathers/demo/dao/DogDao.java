package me.feathers.demo.dao;

import me.feathers.demo.bean.Dog;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * DogDao
 * <p>
 *
 * @author Feathers
 * @date 2018-03-30 14:54
 */
@Repository
public class DogDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public Dog findById(Integer id) {
        String sql = "select * from t_dog where id = ?";
        List<Dog> query = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Dog.class), id);
        return query.size() == 0 ? null : query.get(0);
    }

}
