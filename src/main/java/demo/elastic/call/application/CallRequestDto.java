package demo.elastic.call.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import demo.elastic.call.presentation.CallRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CallRequestDto {

    private String id;

    @JsonFormat
    private JsonNode jsonContents;

    private String wavFileName;     //둘다 파일명
    private String infoJsonFileName;

    @Builder
    public CallRequestDto(String id, JsonNode jsonContents, String wavFileName,
        String infoJsonFileName) {
        this.id = id;
        this.jsonContents = jsonContents;
        this.wavFileName = wavFileName;
        this.infoJsonFileName = infoJsonFileName;
    }

}
