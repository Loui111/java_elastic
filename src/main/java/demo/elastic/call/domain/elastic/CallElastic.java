package demo.elastic.call.domain.elastic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import demo.elastic.call.application.CallRequestDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class CallElastic {

    private String id;

    @JsonFormat
    private JsonNode infoJsonContens;

    private String createdTime;

    private String updatedTime;

    @Builder
    public CallElastic(String id, JsonNode infoJsonContens, String createdTime, String updatedTime) {
        this.id = id;
        this.infoJsonContens = infoJsonContens;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }


    public static CallElastic of(CallRequestDto callRequestDto) {
        return CallElastic.builder()
            .id(callRequestDto.getId())
            .infoJsonContens(callRequestDto.getJsonContents())
            .createdTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) // 2021-09-02T14:56:20.669
            .updatedTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .build();
    }

//    public static CallElastic of(CallRequestDto callRequestDto, String refId) {
//        CallElastic callElastic = CallElastic.builder()
//            .id(refId)
//            .infoJson(callRequestDto.get)
//
//    }
}
