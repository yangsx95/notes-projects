package me.feathers.demo.controller;

import me.feathers.demo.vo.DemoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * JsonController
 * <p>
 *
 * @author Feathers
 * @date 2018-04-01 19:38
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    // 使用GetMapping指定请求类型并建立请求映射 等同于@RequestMapping(methods=Get)
    // SpringBoot默认使用的Json解析框架为Jackson，当方法返回一个对象时，会使用jackson自动解析为json
    @GetMapping("/demo")
    public DemoVO demo1() {
        DemoVO demoVo = new DemoVO();
        demoVo.setId(111L);
        demoVo.setName("张三");
        demoVo.setBirthday(new Date());
        return demoVo;
    }

}
