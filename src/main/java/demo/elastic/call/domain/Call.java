package demo.elastic.call.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import demo.elastic.call.application.CallRequestDto;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "calls")
public class Call {

    @Id
    private String id;      //ES와 같이씀.

    private String infoJsonFileName;

    private String wavFileName;

    private String isDeleted;

//    @Type(type = "json")
//    @Column(columnDefinition = "longtext")  //솔까 얜 필요 없지?
    @Column(name = "json_input", columnDefinition = "json")
    private String infoJsonContents;

    @Builder
    public Call(String id, String infoJsonFileName, String wavFileName, String isDeleted,
        String infoJsonContents) {
        this.id = id;
        this.infoJsonFileName = infoJsonFileName;
        this.wavFileName = wavFileName;
        this.isDeleted = isDeleted;
        this.infoJsonContents = infoJsonContents;
    }

    public static Call of(CallRequestDto callRequestDto) {
        return Call.builder()
            .id(callRequestDto.getId())
            .infoJsonFileName(callRequestDto.getInfoJsonFileName())
            .wavFileName(callRequestDto.getWavFileName())
            .infoJsonContents(callRequestDto.getJsonContents().toString())      //jsonNode --> String
            .isDeleted("false")
            .build();
    }
}