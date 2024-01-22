# Consul

安装consul服务： <https://www.consul.io/downloads>

启动consul服务：
```shell
consul agent -server -bootstrap-expect 1 -data-dir=/tmp/consul -node=n1 -bind=127.0.0.1 -client=0.0.0.0 -ui
```

在配置中心添加配置：
1. 选择左侧的key/value，并添加 `/config/producer/data`
2. 填入key or folder：
3. value值为：
   ```yaml
   config:
       info: "张三"
   ```