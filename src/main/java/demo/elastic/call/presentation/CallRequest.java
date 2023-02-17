package demo.elastic.call.presentation;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CallRequest {

    private String wavFileName;     //둘다 파일명
    private String infoJsonName;

    @Builder
    public CallRequest(String wavFileName, String infoJsonName) {
        this.wavFileName = wavFileName;
        this.infoJsonName = infoJsonName;
    }
}
