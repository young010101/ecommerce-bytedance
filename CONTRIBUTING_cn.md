# 贡献指南

[EN](CONTRIBUTING.md)

感谢您对本项目的关注!这份文档将指导您如何为项目做出贡献。

## 行为准则

请确保您的行为符合我们的行为准则:

- 尊重所有项目参与者
- 使用友善和包容的语言
- 接受建设性的批评
- 关注什么对社区最有利
- 对其他社区成员表示同理心

## 如何贡献

### 报告 Bug

如果您发现了 bug,请通过 GitHub Issues 报告。创建 issue 时:

1. 使用清晰和描述性的标题
2. 详细描述问题和复现步骤
3. 提供相关的系统信息和版本号
4. 如果可能,附上截图或错误日志

### 提出新功能

1. 首先在 Issues 中提出建议
2. 说明这个功能将解决什么问题
3. 讨论可能的实现方案
4. 等待维护者的反馈

### 提交代码

1. Fork 项目仓库
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的修改 (`git commit -m '添加一些功能'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

### 代码规范

- 遵循项目现有的代码风格
- 所有新代码必须包含适当的注释
- 确保代码通过所有测试
- 更新相关文档

## Pull Request 指南

1. 确保 PR 描述清晰地说明了更改内容和原因
2. 更新相关文档
3. 确保所有自动化测试通过
4. 请求至少一位维护者审查

## 开发环境设置

1. 安装必要的开发工具:
   - JDK 17+
   - IDE / 代码编辑器
   - Docker
   - [cwgo](https://github.com/cloudwego/cwgo)
   - kitex
   - [Air](https://github.com/cosmtrek/air)

2. 克隆项目并安装依赖:

    ```sh
    git clone https://github.com/young010101/ecommerce-bytedance.git
    cd ecommerce-bytedance
    ```

3. 启动开发环境:

```sh
make env-start
```

## 获取帮助

如果您需要帮助,可以:

- 查看项目文档
- 在 Issues 中提问
- 联系项目维护者

## 许可证

通过贡献代码,您同意您的贡献将采用与项目相同的许可证授权。
