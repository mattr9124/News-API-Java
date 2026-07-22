package com.mbr.mcp.service

import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.SourcesRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import com.kwabenaberko.newsapilib.models.response.SourcesResponse
import groovy.util.logging.Slf4j
import jakarta.annotation.Resource
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Service

@Slf4j
@Service
class McpService {

    @Resource
    NewsApiClient newsApiClient

    @Tool(name = 'get_top_headlines',
            description = 'Retrieves top news headlines globally')
    ArticleResponse getTopHeadlines(
            @ToolParam(required = false, description = "Search query for specific topics")
                    String q,
            @ToolParam(required = false, description = "Category of headlines, e.g. business, entertainment, health, science, sports, technology")
                    String category,
            @ToolParam(required = false, description = "Comma-separated list of news source identifiers")
                    String sources,
            @ToolParam(required = false, description = "2-letter ISO 3166-1 country code, e.g. us, gb, au")
                    String country,
            @ToolParam(required = false, description = "2-letter ISO 639-1 language code, e.g. en, es, fr")
                    String language,
            @ToolParam(required = false, description = "Number of results per page")
                    Integer pageSize,
            @ToolParam(required = false, description = "Page number to retrieve")
                    Integer page) {

        log.info("getTopHeadlines called with q={}, category={}, sources={}, country={}, language={}", q, category, sources, country, language)
        def result = newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q(q)
                        .category(category)
                        .sources(sources)
                        .country(country)
                        .language(language)
                        .pageSize(pageSize)
                        .page(page)
                        .build()


        ).body()
        log.info("getTopHeadlines returning {} articles", result?.articles?.size())
        result
    }

    @Tool(name = 'get_everything',
            description = "Retrieves all news articles available by the News API")
    ArticleResponse getEverything(
            @ToolParam(required = false, description = "Search query for specific topics")
                    String q,
            @ToolParam(required = false, description = "Comma-separated list of news source identifiers")
                    String sources,
            @ToolParam(required = false, description = "Comma-separated list of domains to restrict search to, e.g. bbc.co.uk,techcrunch.com")
                    String domains,
            @ToolParam(required = false, description = "Oldest article date in ISO 8601 format, e.g. 2024-01-01")
                    String from,
            @ToolParam(required = false, description = "Newest article date in ISO 8601 format, e.g. 2024-12-31")
                    String to,
            @ToolParam(required = false, description = "2-letter ISO 639-1 language code, e.g. en, es, fr")
                    String language,
            @ToolParam(required = false, description = "Sort order: relevancy, popularity, or publishedAt")
                    String sortBy,
            @ToolParam(required = false, description = "Number of results per page")
                    Integer pageSize,
            @ToolParam(required = false, description = "Page number to retrieve")
                    Integer page) {

        log.info("getEverything called with q={}, sources={}, domains={}, from={}, to={}", q, sources, domains, from, to)
        def result = newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(q)
                        .sources(sources)
                        .domains(domains)
                        .from(from)
                        .to(to)
                        .language(language)
                        .sortBy(sortBy)
                        .pageSize(pageSize)
                        .page(page)
                        .build()


        ).body()
        log.info("getEverything returning {} articles", result?.articles?.size())
        result
    }

    @Tool(name = 'get_sources',
            description = 'Retrieves news sources available by the News API')
    SourcesResponse getSources(
            @ToolParam(required = false, description = "Category of sources, e.g. business, entertainment, health, science, sports, technology")
                    String category,
            @ToolParam(required = false, description = "2-letter ISO 639-1 language code, e.g. en, es, fr")
                    String language,
            @ToolParam(required = false, description = "2-letter ISO 3166-1 country code, e.g. us, gb, au")
                    String country) {

        log.info("getSources called with category={}, language={}, country={}", category, language, country)
        def result = newsApiClient.getSources(
                new SourcesRequest.Builder()
                        .category(category)
                        .language(language)
                        .country(country)
                        .build()
        ).body()
        log.info("getSources returning {} sources", result?.sources?.size())
        result
    }
}
