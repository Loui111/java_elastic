package demo.elastic.call.presentation;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CallResponse {

    private Long id;
    private String name;
    private String infoJson;

    @Builder
    public CallResponse(Long id, String name, String infoJson) {
        this.id = id;
        this.name = name;
        this.infoJson = infoJson;
    }
}
