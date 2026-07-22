package com.kwabenaberko.newsapilib;

import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.SourcesRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.models.response.SourcesResponse;
import com.kwabenaberko.newsapilib.network.APIClient;
import com.kwabenaberko.newsapilib.network.APIService;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NewsApiClient {
    private String mApiKey;
    private Map<String, String> query;
    private APIService mAPIService;

    public NewsApiClient(String apiKey){
        mApiKey = apiKey;
        mAPIService = APIClient.getAPIService();
        query = new HashMap<>();
        query.put("apiKey", apiKey);
    }

    private Map<String, String> createQuery(){
        query = new HashMap<>();
        query.put("apiKey", mApiKey);
        return query;
    }


    //Get Sources
    public Response<SourcesResponse> getSources(SourcesRequest sourcesRequest){
        query = createQuery();
        query.put("category", sourcesRequest.getCategory());
        query.put("language", sourcesRequest.getLanguage());
        query.put("country", sourcesRequest.getCountry());

        query.values().removeAll(Collections.singleton(null));


        try {
            return mAPIService.getSources(query).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Response<ArticleResponse> getTopHeadlines(TopHeadlinesRequest topHeadlinesRequest){


        query = createQuery();
        query.put("country", topHeadlinesRequest.getCountry());
        query.put("language", topHeadlinesRequest.getLanguage());
        query.put("category", topHeadlinesRequest.getCategory());
        query.put("sources", topHeadlinesRequest.getSources());
        query.put("q", topHeadlinesRequest.getQ());
        query.put("pageSize", topHeadlinesRequest.getPageSize());
        query.put("page", topHeadlinesRequest.getPage());

        query.values().removeAll(Collections.singleton(null));
        query.values().removeAll(Collections.singleton("null"));

        try {
            return mAPIService.getTopHeadlines(query).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Response<ArticleResponse> getEverything(EverythingRequest everythingRequest){
        query = createQuery();
        query.put("q", everythingRequest.getQ());
        query.put("sources", everythingRequest.getSources());
        query.put("domains", everythingRequest.getDomains());
        query.put("from", everythingRequest.getFrom());
        query.put("to", everythingRequest.getTo());
        query.put("language", everythingRequest.getLanguage());
        query.put("sortBy", everythingRequest.getSortBy());
        query.put("pageSize", everythingRequest.getPageSize());
        query.put("page", everythingRequest.getPage());

        query.values().removeAll(Collections.singleton(null));
        query.values().removeAll(Collections.singleton("null"));

        try {
            return mAPIService.getEverything(query).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}