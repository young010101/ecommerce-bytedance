package com.sky.eshop.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Map;

/**
 * MyBatis-Plus code generator utility class.
 */
public final class CodeGenerator {
    /**
     * Database URL for connection.
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/e_shop";

    /**
     * Database username.
     */
    private static final String DB_USERNAME = "root";

    /**
     * Database password.
     */
    private static final String DB_PASSWORD = "root";

    /**
     * Parent package name for generated code.
     */
    private static final String PARENT_PACKAGE = "com.sky.eshop";

    /**
     * Author name for generated code.
     */
    private static final String AUTHOR = "Cheng Yang";

    /**
     * Tables to generate code for.
     */
    private static final String[] TABLES = {
        "product",
        "category",
        "product_category"
    };

    /**
     * Private constructor to prevent instantiation.
     */
    private CodeGenerator() {
    }

    /**
     * Main method to run the code generator.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        generateCode();
    }

    /**
     * Generate code based on module type.
     */
    private static void generateCode() {
        final String outputDir = System.getProperty("user.dir")
            + "/sky-eshop/src/main/";
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                // Global configuration
                .globalConfig(builder -> builder
                        .author(AUTHOR)
                        .outputDir(outputDir + "java")
                        .commentDate("yyyy-MM-dd")
                        .enableSpringdoc()
                        .disableOpenDir()
                )
                // Package configuration
                .packageConfig(builder -> builder
                        .parent(PARENT_PACKAGE)
                        .pathInfo(Map.of(
                                OutputFile.xml,
                                outputDir + "resources/mapper"
                        ))
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .addInclude(TABLES)
                        .entityBuilder()
                        .enableLombok()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
