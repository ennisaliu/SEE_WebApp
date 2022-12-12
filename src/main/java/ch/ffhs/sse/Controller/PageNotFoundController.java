package ch.ffhs.sse.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
public class PageNotFoundController {
    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }
}

