package com.mbr.mcp

import com.kwabenaberko.newsapilib.NewsApiClient
import com.mbr.mcp.service.McpService
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackages = 'com.mbr.mcp.service')
class McpApp {
    @Value('${news.api.key}')
    String newsApiKey

    static void main(String[] args) {
        SpringApplication.run McpApp.class
    }

    @Bean
    ToolCallbackProvider newsApiTool(McpService mcpService) {
        MethodToolCallbackProvider.builder()
                .toolObjects(mcpService)
                .build()
    }

    @Bean
    NewsApiClient newsApiClient() {
        new NewsApiClient(newsApiKey)
    }
}
