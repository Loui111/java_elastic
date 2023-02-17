package demo.elastic.call.application.elastic;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CallElasticResponseDto {

    private String id;
    private String _index;
    private String _id;
    private String _score;
    private String _source;
    private String infoJsonContens;
}
