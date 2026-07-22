## Fork details
I forked the original repo and added an MCP module, I've also change the original implementation to work in synchronous mode rather than async as it is more suitable for this MCP.

You can build the JAR with:
```shell
# builds the jar to libs/mcp-runner-0.0.1-SNAPSHOT.jar
./gradlew :mcp-runner:bootJar

# run with
java -jar ${path_to_jar}
```
Configure MCP server with something like this (Claude Desktop example):
```json
"mcpServers": {
  "newsApi": {
    "command": "java",
    "args": [
      "-jar",
      "${BASE_PATH}/mcp-runner/build/libs/mcp-runner-0.0.1-SNAPSHOT.jar"
    ]
  }
}
```


## News-API-Java

**Create an account at [newsapi.org](https://newsapi.org/) to get your API key.**


## Download

### Using Gradle

#### Step 1. Add the JitPack repository to your root ```build.gradle``` file.

``` java
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

#### Step 2 : Download via ```Gradle```:

```java
implementation 'com.github.KwabenBerko:News-API-Java:1.0.2'
```

### Using Maven

#### Step 1. Add the JitPack repository to your ```pom.xml``` file.

``` java
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

#### Step 2 : Add the dependency to the dependencies in your ```pom.xml``` file:

```java
<dependencies>
  ...
  ...
  <dependency>
    <groupId>com.github.KwabenBerko</groupId>
    <artifactId>News-API-Java</artifactId>
    <version>1.0.2</version>
  </dependency>
</dependencies>
```

## Usage

#### Instantiate the NewsApiClient class:

``` java 
NewsApiClient newsApiClient = new NewsApiClient("YOUR_API_KEY");
```

#### Get Top Headlines

```java
newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q("bitcoin")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println(response.getArticles().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                       System.out.println(throwable.getMessage());
                    }
                }
        );
```

#### Get Everything

```java
newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("trump")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println(response.getArticles().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
```

#### Get Sources
```java
newsApiClient.getSources(
                new SourcesRequest.Builder()
                        .language("en")
                        .country("us")
                        .build(),
                new NewsApiClient.SourcesCallback() {
                    @Override
                    public void onSuccess(SourcesResponse response) {
                        System.out.println(response.getSources().get(0).getName());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
```




