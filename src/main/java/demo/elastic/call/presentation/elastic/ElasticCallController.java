package demo.elastic.call.presentation.elastic;

import demo.elastic.call.application.CallService;
import demo.elastic.call.application.elastic.ElasticCallService;
import java.io.IOException;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/call")
public class ElasticCallController {

//    private final CallService callService;
    private final ElasticCallService elasticCallService;

    @GetMapping("/_search")
    public Object getCall(@RequestBody HashMap<String, Object> param ) throws IOException {

        elasticCallService.read(param);

        return null;
    }
}
