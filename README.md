# 网关设计

## 代码结构

* zhacker-discovery: 注册中心
* zhacker-gateway-passport: 认证中心
* zhacker-gateway-route: 资源中心
* zhacker-gateway-admin: 示例应用

## 如何使用

1. 下载代码

```
git clone https://github.com/fancyyawn/zhacker-gateway.git
```

2. 启动[eureka服务注册中心](http://localhost:8761)

``` 
mvn spring-boot:run -pl 'zhacker-discovery'
```

3. 启动[网关的认证中心](http://localhost:13000)


本系统依赖于redis，请确保开放了6379端口

``` 
mvn spring-boot:run -pl 'zhacker-gateway-passport'
```

4. 启动[网关的资源中心](http://localhost:14000)

``` 
mvn spring-boot:run -pl 'zhacker-gateway-route'
```

5. 启动一个[应用示例](http://localhost:12000)

``` 
mvn spring-boot:run -pl 'zhacker-gateway-admin'
```

6. 访问页面

* 访问应用示例
* 点击登录按钮，跳转到认证中心
* 输入用户名和密码（admin/123456）
* 跳转到授权页面进行授权
* 跳转回应用首页