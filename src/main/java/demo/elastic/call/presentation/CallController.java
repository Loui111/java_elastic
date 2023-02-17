package demo.elastic.call.presentation;

import demo.elastic.call.application.CallRequestDto;
import demo.elastic.call.application.CallService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "${apiVersion}/call")
public class CallController {
    private final CallService callService;

    @PostMapping("")
    public Object postCall(@RequestBody CallRequest callRequest) throws Exception{
//        Object save = callService.save(CallRequestDto.of(callRequest));
        Object save = callService.save(callRequest);

        return save;

//        return callService.postCall(memberRequest);
    }
}
