package demo.elastic.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import java.io.File;
import java.io.IOException;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig{

    @Bean
    public ElasticsearchClient ESConfig() throws IOException {

        String TEMP_PATH = "tmp/";

        String login = "elastic";
        String password = "aSQFH_0iOUaU8H5It=0T";
        String host = "localhost";
        int port = 9200;

        File certFile = new File("/Users/loui/Posicube/backend/demo-elastic/src/main/resources/http_ca.crt");

        SSLContext sslContext = TransportUtils
            .sslContextFromHttpCaCrt(certFile);

        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
            AuthScope.ANY, new UsernamePasswordCredentials(login, password)
        );

        RestClient restClient = RestClient
            .builder(new HttpHost(host, port, "https"))
            .setHttpClientConfigCallback(hc -> hc
                .setSSLContext(sslContext)
                .setDefaultCredentialsProvider(credsProv)
            )
            .build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);

        return client;
    }
}
