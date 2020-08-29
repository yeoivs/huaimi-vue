package com.ieng.huaimi.core.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = {"com.ieng.huaimi.database.mapper"})
public class MapperConfig {
}
