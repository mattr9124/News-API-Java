import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

def apiKey = 'c5fedb86dff94a569e2bb1c73f44b9ef'

def newsClient = new NewsApiClient(apiKey)


def response = newsClient.getTopHeadlines(new TopHeadlinesRequest.Builder()
        .q('')
        .language('en')
        .build())

def body = response.body()
println body.status
println body.totalResults
body.articles.each {
    println "${it.title} - ${it.author} - ${it.url}"
}

