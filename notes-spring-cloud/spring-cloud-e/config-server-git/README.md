# 分布式配置中心 服务端

整体思路如下：

> 分布式配置中心负责从配置仓库中读取配置文件，映射为url，这里的配置仓库可以是本地文件、git远程仓库等。
> 客户端会从配置中心获取自己的配置文件，并使用，从而实现配置文件集中化、版本化的管理。

# 流程

1. 在仓库下的master分支下的某个目录下建立yml文件
2. 文件名称遵循以下规则：{application}-{profile}.yml
3. 其中，application代表客户端的名称（映射客户端配置：spring.application.name）
4. profile 代表配置类型名称，比如dev、product、default等。映射客户端配置：spring.profiles.active（启用的配置类型）
5. 客户端会从配置中心使用http请求的方式获取自己的配置
6. 客户端会使用以下url获取自己的配置：
7. {config-server}/{application}/{profile}/{label}
8. 其中，label代表仓库分支，默认为master分支，对应的客户端配置为 spring.

获取到的资源为一个json文件，示例如下：
访问 http://localhost:8763/config-client/dev/master
```json
{
	"name": "config-client",
	"profiles": ["dev"],
	"label": "master",
	"version": "8f0393dfc7ce0758744aa49c0f6db23a574b6ffd",
	"state": null,
	"propertySources": [{
		"name": "https://github.com/xf616510229/repo.git/spring-cloud-config/config-client-dev.yml",
		"source": {
			"info.profile": "dev",
			"info.from": "git-repo"
		}
	}]
}
```
其中source参数就是配置文件的内容，这样客户端就从配置中心取到了自己的配置了spring.cloud.config.label


# 获取纯文本配置

${server-url}/${application}/${profile}/${label}/${filePath}
http://localhost:8763/config-client/default/master/logback.xml
http://localhost:8763/config-client/default/master/nginx.conf

# 配置加密
