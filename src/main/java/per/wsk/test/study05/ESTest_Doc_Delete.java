package per.wsk.test.study05;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTest_Doc_Delete {

    /**
     * 删除索引中的某一条数据
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        DeleteRequest request = new DeleteRequest();
        request.index("country").id("10002");

        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());

        esClient.close();

    }

}
