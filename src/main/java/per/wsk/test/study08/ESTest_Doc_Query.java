package per.wsk.test.study08;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;



import java.io.IOException;

public class ESTest_Doc_Query {


    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //1.查询索引中全部的数据
        /*SearchRequest request = new SearchRequest();
        request.indices("country");

        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit:hits) {
            System.out.println(hit.getSourceAsString());
        }*/

        //2.条件查询
        /*SearchRequest request = new SearchRequest();
        request.indices("country");

        //查询age等于270的数据
        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 270)));

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }*/

        //3. 分页查询
        /*SearchRequest request = new SearchRequest();
        request.indices("country");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        *//*builder.from(8);//从第几条开始查询 （）
        builder.size(4);//每页显示多少*//*
        //以上是从第8条开始往后数4个（0是起始的第一个），也就是查询的是第3页
        builder.from(4);
        builder.size(4);
        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }*/

        // 4. 查询排序
        /*SearchRequest request = new SearchRequest();
        request.indices("country");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //age来倒序排列
        builder.sort("age", SortOrder.DESC);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }*/

        //5.过滤字段
        /*SearchRequest request = new SearchRequest();
        request.indices("country");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //
        String[] excludes = {"age"};//排除哪些字段不显示
        String[] includes = {};
        builder.fetchSource(includes, excludes);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }*/

        //6.组合查询
        /*SearchRequest request = new SearchRequest();
        request.indices("country");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //boolQueryBuilder.must(QueryBuilders.matchQuery("age", 270));      // age必须是270
        //boolQueryBuilder.must(QueryBuilders.matchQuery("name", "美国"));    // name必须是美国
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name", "日本")); // name必须不是日本
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 270)); //age是270
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 1270));//age是1270
        //带must的表示必须满足，多个must条件一起组合，表示这些条件都要同时满足
        // 多个should一起组合的条件表示或者，即满足这些条件的其中之一的数据就会被查出来

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boolQueryBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }*/

        // 7. 范围查询
        SearchRequest request = new SearchRequest();
        request.indices("country");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");

        rangeQuery.gte(100);//gt是大于  gte是大于等于
        rangeQuery.lt(3000);//lt是小于  lte是小于等于

        builder.query(rangeQuery);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }



        esClient.close();
    }


}
