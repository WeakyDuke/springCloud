package duke.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duke
 * @version 1.0
 * @Description TODO
 * @date 2019/7/16 11:59
 */
@RestController
public class HelloController {

    @Value("${spring.application.name}")
    String applicationName;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return applicationName + ": Hello World";
    }
}
