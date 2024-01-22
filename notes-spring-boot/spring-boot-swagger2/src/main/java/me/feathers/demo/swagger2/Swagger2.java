package me.feathers.demo.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置类
 * <p>
 *
 * @author Feathers
 * @date 2018-05-31 15:27
 */
// 使用Configuration注解，声明该类是一个配置，再使用EnableSwagger2注解启用Swagger2
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * 创建Docket对象
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 设置API的基本信息
                .select() // 创建一个ApiSelectorBuilder 用来指定哪些接口暴露给Swagger展示
                .apis(RequestHandlerSelectors.basePackage("me.feathers.demo")) //在me.feathers.demo包下
                .paths(PathSelectors.any()) // 的所有路径的接口，都可以暴露给swagger
                .build();
    }

    /**
     * 配置Api的基本信息
     */
    private ApiInfo apiInfo() {
        List<VendorExtension> extensions = new ArrayList<>();
        extensions.add(new StringVendorExtension("author", "Feathers"));

        return new ApiInfoBuilder()
                .title("SpringBoot Swagger Api Demo") // 更新API名称
                .description("在SpringBoot应用中使用Swagger2构建Restful API") // 更新API描述
                .version("1.0") // 更新API版本
                .contact(new Contact("Feathers", "https://github.com/xf616510229", "616510229@qq.com")) // 更新 API负责人的联系信息
                .termsOfServiceUrl("http://termOfServiceUrl.com")
                //.license("") // 更新license
                //.licenseUrl("") // 更新license的地址
                .extensions(extensions) // 设置其他额外信息
                .build();
    }
}
