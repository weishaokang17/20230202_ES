package per.wsk.test.study03;


import org.apache.http.HttpHost;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


import java.io.IOException;

/**
 * 向索引中修改数据
 */
public class ESTest_Doc_Update {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 修改数据
        UpdateRequest request = new UpdateRequest();
        request.index("country").id("10002");
        request.doc(XContentType.JSON,"capital","上海");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());


        esClient.close();

    }
}
