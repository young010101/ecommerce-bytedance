# e-commerce

[EN](README.md)

## 技术栈

| 技术 | 说明 |
|-----|-----|
| Spring Boot | 后端框架 |
| MySQL | 关系型数据库 |
| Redis | 缓存数据库 |
| MyBatis | ORM框架 |
| Swagger | API文档生成 |
| Spring Task | 定时任务 |
| JWT | 认证授权 |
| WebSocket | 消息推送 |
| AliOSS | 阿里云对象存储 |
| Spring AOP | 面向切面编程 |

## 业务逻辑

- [x] 页面访问认证检查, 基于 JWT 的认证
- [ ] 注册
- [x] 登录
- [ ] 退出
- [x] 产品分类
- [x] 产品
- [ ] 加购
- [ ] 购物车数量角标
- [ ] 下单
- [ ] 支付
- [ ] 订单中心

## 如何使用

### 准备

必备清单

- JDK 17+
- IDE / Code Editor
- Docker
- [cwgo](https://github.com/cloudwego/cwgo)
- kitex `go install github.com/cloudwego/kitex/tool/cmd/kitex@latest`
- [Air](https://github.com/cosmtrek/air)
- ...

### 克隆项目

```sh
git clone ...
```

### 拷贝 `.env` 文件

配置`application-dev.yaml`

```sh
make init
```

*Note:*`必须生成并输入 SESSION_SECRET 值供 session 功能正常使用`

### 下载 Go 依赖

```sh
make tidy
```

### 启动容器

```sh
make env-start
```

if you want to stop their docker application,you can run `make env-stop`.

### 启动某服务

该命令必须执行一个服务

*注意:* `我们使用 air 运行并热加载，必须先安装好`

```sh
make run svc=`svcName`
```

### 浏览 Gomall 站点

```sh
make open-gomall
```

### 查看注册中心

```sh
make open-consul
```

### Make 用法

```sh
make
```

## 贡献者

- [rogerogers](https://github.com/rogerogers)
- [baiyutang](https://github.com/baiyutang)
