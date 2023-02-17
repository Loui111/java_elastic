package demo.elastic.call.application.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.PutScriptResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.elastic.call.presentation.CallRequest;
import demo.elastic.configuration.ElasticSearchConfig;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Slf4j
@RequiredArgsConstructor
@ResponseBody
public class ElasticCallService {

    ObjectMapper mapper = new ObjectMapper();
    private final ElasticSearchConfig elasticSearchConfig;

    public Object read(HashMap<String, Object> param) throws IOException {

        ElasticsearchClient elasticsearchClient = elasticSearchConfig.ESConfig();
//        Query resultCodeQuery = new Query();
//        resultCodeQuery = null;

        JsonNode jsonNodes = mapper.valueToTree(param);

//        Query resultCodeQuery = null;
//        for (JsonNode jsonNode : jsonNodes.get("query").get("must")) {
////            if(jsonNode.get("match").has("infoJsonContens.call_result_code")){
//            resultCodeQuery = getResultCode(jsonNode);
////            }
//        }
//
//        String field = "infoJsonContens.call_result_code";
//        String query = "002";
//
//        String field = jsonNode.get("query").get("must").get("match").asText();
//        String query = jsonNode.get("query").get("must").get("match").get(field).asText();

        //단건

//        Query resultCode = getResultCode(jsonNodes);

        SearchResponse<Object> response = elasticsearchClient.search(s -> s
            .index("call")
            .query(q -> q
                .bool(b -> b
                    .must(getResultCode(jsonNodes))
                )
            ), Object.class
        );

        String result = response.toString();
        JsonNode jsonNode1 = mapper.readTree(result);

//        SearchResponse<CallElasticResponseDto> response = elasticsearchClient.search(s -> s
//                .index("call")
//                .query(q -> q
//                    .match(t -> t
//                        .field("infoJsonContens.call_result_code")
//                        .query("002")
//                    )
//                ),
//            CallElasticResponseDto.class
//        );

        response.toString();

//        Object query = param.get("query");
//        String str = param.get("query").get("bool").get("must").get(0).get("match").get("infoJsonContens.call_result_code");

//        JsonNode jsonNode = mapper.valueToTree(param);
//        String str = jsonNode.get("query").get("must").get("match").get("infoJsonContens.call_result_code").asText();

//        PutScriptResponse mustache = elasticsearchClient.putScript(r -> r
//            .id("query-script")
//            .script(s -> s
//                    .lang("mustache")
//                    .source(jsonNode.toString())
////                .source("{\"query\":{\"match\":{\"{{field}}\":\"{{value}}\"}}}")
//            ));

//        log.info();

        return null;
    }

    private Query getResultCode(JsonNode jsonNode) {
        Query byName = MatchQuery.of(m -> m
            .field("infoJsonContens.call_result_code")
            .query("002")
        )._toQuery();

        return byName;
    }
}
