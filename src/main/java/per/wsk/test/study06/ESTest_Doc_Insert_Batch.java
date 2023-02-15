package per.wsk.test.study06;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTest_Doc_Insert_Batch {


    /**
     * 向某索引中批量添加数据
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        BulkRequest request = new BulkRequest();

//        request.add(new IndexRequest().index("country").id("10002").source(XContentType.JSON,"name","美国","age",270,"capital","华盛顿"));
        request.add(new IndexRequest().index("country").id("10003").source(XContentType.JSON,"name","英国","age",2270,"capital","伦敦"));
        request.add(new IndexRequest().index("country").id("10004").source(XContentType.JSON,"name","法国","age",1270,"capital","巴黎"));
        request.add(new IndexRequest().index("country").id("10005").source(XContentType.JSON,"name","德国","age",1170,"capital","柏林"));
        request.add(new IndexRequest().index("country").id("10006").source(XContentType.JSON,"name","日本","age",3270,"capital","东京"));
        request.add(new IndexRequest().index("country").id("10007").source(XContentType.JSON,"name","瑞士","age",770,"capital","日内瓦"));
        request.add(new IndexRequest().index("country").id("10008").source(XContentType.JSON,"name","澳大利亚","age",170,"capital","堪培拉"));
        request.add(new IndexRequest().index("country").id("10009").source(XContentType.JSON,"name","加拿大","age",180,"capital","渥太华"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response.getTook());
        System.out.println(response.getItems());

        esClient.close();


    }

}
