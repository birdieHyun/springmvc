package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")        // 뷰에 대한 것을 정리해야 하기 때문에 ModelAndView 부터 시작한다.
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return mav;
    }

//    @ResponseBody  // ResponseBody를 사용하면 retur값이 Http 응답 메시지로 나가고 뷰를 찾지 않게 된다.
    @RequestMapping("/response-view-v2")        // 뷰에 대한 것을 정리해야 하기 때문에 ModelAndView 부터 시작한다.
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello");
        return "response/hello";       // 뷰의 논리적인 이름이 된다.
    }

    @RequestMapping("/response/hello")      // 권장하지 않는 방법,
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello");
    }
}
