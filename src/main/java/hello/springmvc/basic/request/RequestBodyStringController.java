package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// StreamUtil로 들어오면 바이트코드이기 때문에 어떻게 인코딩할지 설정해주어야 한다.

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWirter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        responseWirter.write("ok");
    }

//    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
//        String messageBody = httpEntity.getBody(); //http content에 있는 body를 꺼낸다.
//        log.info("messageBody={}", messageBody);
//
//        return new HttpEntity<>("ok");       // 첫 번째 파라미터에 바디 메시지를 넣을 수 있다.
//    }

    // v3 다르게 사용하는 방법
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }


//    @PostMapping("/request-body-string-v4")
//    public HttpEntity<String> requestBodyStringV4(@RequestBody String messageBody) {  // @RequestBody가 다른 코드 필요없이, HTTP 메시지 바디를 찾아서 딱 해결해준다.
//
//        log.info("messageBody={}", messageBody);
//
//        return new ResponseEntity<>("ok", HttpStatus.CREATED);
//    }

    @ResponseBody     // String으로 반환하면 http message body에 return값을 딱 넣어준다.
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
