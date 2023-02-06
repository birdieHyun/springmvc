package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController는 문자를 리턴하면 String 그대로 반환이 되어서 화면에 보이게 된다.
// Controller 의 리턴값은 View 값이 반환되어서 ViewResolver 를 거쳐야 한다.
@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("log-test")
    private String LogTest(){
        String name = "Spring";

        // 과거
        System.out.println("name = " + name);

        // 밑의 두 개가 같은 것이다. -> 의미없는 연산을 방지할 수 있다.
//        if (log.isTraceEnabled()) {
//            log.trace("trace log =" + name);
//        }
//
//        log.trace("trace log{}=", name);

        //로그를 사용하면
        log.trace("trace log={}", name);
        log.debug("debug log{}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
