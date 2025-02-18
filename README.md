# :cloud: Gomall

![Build](https://github.com/young010101/ecommerce-bytedance/actions/workflows/ci.yml/badge.svg)
[![codecov](https://codecov.io/gh/young010101/ecommerce-bytedance/branch/dev/graph/badge.svg)](https://codecov.io/gh/young010101/ecommerce-bytedance)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/0f64f888c06e4bf3acf7d4e0812ca7a0)](https://app.codacy.com/gh/young010101/ecommerce-bytedance/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=young010101_ecommerce-bytedance&metric=alert_status)](https://sonarcloud.io/dashboard?id=young010101_ecommerce-bytedance)

[中文](README_cn.md)

This is a teaching project for newbie using CloudWeGo

## Technology Stack

| technology | introduce |
|---------------|----|
| cwgo          | -  |
| kitex         | -  |
| [bootstrap](https://getbootstrap.com/docs/5.3/getting-started/introduction/) | Bootstrap is a powerful, feature-packed frontend toolkit. Build anything—from prototype to production—in minutes.  |
| Hertz         | -  |
| MySQL         | -  |
| Redis         | -  |
| ES            | -  |
| Prometheus    | -  |
| Jaeger        | -  |
| Docker        | -  |

## Biz Logic

- [x] The pages check auth
- [x] Register
- [x] Login
- [x] Logout
- [x] Product categories
- [x] Products
- [x] Add to cart
- [x] The number badge of cart products
- [x] Checkout
- [x] Payment
- [x] Orders center

## How to use

### Prepare

List required

- JDK 17+
- IDE / Code Editor
- Docker
- [cwgo](https://github.com/cloudwego/cwgo)
- kitex `go install github.com/cloudwego/kitex/tool/cmd/kitex@latest`
- [Air](https://github.com/cosmtrek/air)
- ...

### Clone code

```sh
git clone ...
```

### Copy `.env` file

```sh
make init
```

*Note:*`You must generate and input SESSION_SECRET random value for session`

### Download go module

```sh
make tidy
```

### Start Docker Compose

```sh
make env-start
```

if you want to stop their docker application,you can run `make env-stop`.

### Run Service

This cmd must appoint a service.

*Note:* `Run the Go server using air. So it must be installed`

```sh
make run svc=`svcName`
```

### View Gomall Website

```sh
make open-gomall
```

### Check Registry

```sh
make open-consul
```

### Make Usage

```sh
make
```

## Contributors

- [young010101](https://github.com/young010101)
