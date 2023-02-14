package per.wsk.test.study03;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import per.wsk.test.study02.Country;

import java.io.IOException;

/**
 * 向索引中添加数据
 */
public class ESTest_Doc_Insert {


    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 插入数据
        IndexRequest request = new IndexRequest();
        request.index("country").id("10002");

        Country country = new Country();
        country.setName("中国");
        country.setAge(4770);
        country.setCapital("北京");

        // 向ES插入数据，必须将数据转换位JSON格式
        ObjectMapper mapper = new ObjectMapper();
        String countryJson = mapper.writeValueAsString(country);
        request.source(countryJson, XContentType.JSON);

        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());

        esClient.close();

    }

}
