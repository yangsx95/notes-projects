package me.feathers.demo.service;

import me.feathers.demo.bean.Dog;
import me.feathers.demo.dao.DogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * DogService
 * <p>
 *
 * @author Feathers
 * @date 2018-03-30 15:07
 */
@Service
public class DogService {

    @Resource
    private DogDao dogDao;

    public Dog get(Integer id) {
        return dogDao.findById(id);
    }

}
