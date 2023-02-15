package per.wsk.test.study04;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTest_Doc_Get {

    /**
     * 查询索引中的某一条数据
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //查询数据
        GetRequest request = new GetRequest();
        request.index("country").id("10002");
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());

        esClient.close();

    }

}
