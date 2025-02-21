# Proto 文件的源目录
PROTO_SRC_DIR = ./idl
# Java 文件输出目录
JAVA_OUT_DIR = sky-pojo/src/main/java

# 默认目标
.PHONY: proto
proto:
	protoc --java_out=$(JAVA_OUT_DIR) $(PROTO_SRC_DIR)/response.proto

# 清理生成的文件
.PHONY: clean
clean:
	rm -rf $(JAVA_OUT_DIR)/**/response.java

# 帮助信息
.PHONY: help
help:
	@echo "Available targets:"
	@echo "  proto  - Generate Java classes from proto files"
	@echo "  clean  - Remove generated Java files"
