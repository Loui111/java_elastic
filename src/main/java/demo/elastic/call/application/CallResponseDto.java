package demo.elastic.call.application;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CallResponseDto {

    private Long id;
    private String name;
    private String infoJson;

    @Builder
    public CallResponseDto(String name, String infoJson) {
        this.name = name;
        this.infoJson = infoJson;
    }
}
