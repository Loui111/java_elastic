package demo.elastic.call.application;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.elastic.call.domain.Call;
import demo.elastic.call.domain.elastic.CallElastic;
import demo.elastic.call.infrastructure.call.CallRepository;
import demo.elastic.call.presentation.CallRequest;
import demo.elastic.configuration.ElasticSearchConfig;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Slf4j
@RequiredArgsConstructor
@ResponseBody
public class CallService {

    private final CallRepository callRepository;
    private final ElasticSearchConfig elasticSearchConfig;
//    private final JsonNode jsonNode;
    private final ObjectMapper objectMapper;

    public Object save(CallRequest callRequest) throws IOException {

        String jsonFileName1 = "/Users/loui/Posicube/backend/demo-elastic/src/main/resources/infojsons/80d08092-e47c-41dc-980d-e5e0face53ad.json";
        String jsonFileName2 = "/Users/loui/Posicube/backend/demo-elastic/src/main/resources/infojsons/80d21296-1d94-4088-ba20-f682a951ac56.json";
        String jsonFileName3 = "/Users/loui/Posicube/backend/demo-elastic/src/main/resources/infojsons/c0b91ac8-1c4a-445d-928d-98d9fda1c890.json";
        String jsonFileName4 = "/Users/loui/Posicube/backend/demo-elastic/src/main/resources/infojsons/ffea85c0-7052-4069-91e3-c8f14aa1617b.json";
        String jsonFileName5 = "/Users/loui/Posicube/backend/demo-elastic/src/main/resources/infojsons/80d671fe-7c4b-41c4-97e4-1fc70e6879a3.json";
        String jsonFileName6 = "/Users/loui/Posicube/backend/demo-elastic/src/main/resources/infojsons/80d7d3dc-6c18-402f-a37c-7c4df8ec35c2.json";
        String jsonFileName7 = "/Users/loui/Posicube/backend/demo-elastic/src/main/resources/infojsons/80de7a4a-9d15-4936-96ee-c02f54c37edc.json";

        String wavFileName1 = "tempFile5.wav";

        String refId = UUID.randomUUID().toString();
        CallRequestDto callRequestDto = getCallRequestDto(jsonFileName7, wavFileName1, refId);

        //RDB 저장
        Call call = Call.of(callRequestDto);
        Call call1 = callRepository.save(call);

        log.info("id:: ", call1.getId());

        return call1;
    }

    private CallRequestDto getCallRequestDto(String jsonFileName1, String wavFileName1, String refId) throws IOException {
        ElasticsearchClient elasticsearchClient = elasticSearchConfig.ESConfig();

        JsonNode jsonContents = readFileAsString(jsonFileName1);

        CallRequestDto callRequestDto = getCallRequestDto(jsonFileName1, wavFileName1, jsonContents, refId);

        //ES 저장
        //CallElastic = ES저장용 객체
        CallElastic callElastic = CallElastic.of(callRequestDto);

        //https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/indexing.html
        IndexRequest.Builder<CallElastic> indexReqBuilder = new IndexRequest.Builder<>();
        indexReqBuilder.index("call");      //ES에서 Database라고 보면 됨.
        indexReqBuilder.id(refId);          //callElastic.getId()
        indexReqBuilder.document(callElastic);      //ES에서 row라고 보면 됨. 저장하는 실제 데이터 json

        IndexResponse response = elasticsearchClient.index(indexReqBuilder.build());
        //ES 저장.
        return callRequestDto;
    }

    private CallRequestDto getCallRequestDto(String jsonFileName1, String wavFileName1,
        JsonNode jsonContents, String refId) {
        CallRequestDto callRequestDto = CallRequestDto.builder()
            .id(refId)
            .infoJsonFileName(jsonFileName1)
            .wavFileName(wavFileName1)
            .jsonContents(jsonContents)
            .build();
        return callRequestDto;
    }

    public JsonNode readFileAsString(String file) throws IOException {
        File tempfile = new File(file);

        JsonNode jsonNode = objectMapper.readTree(tempfile);
        return jsonNode;
    }
}
