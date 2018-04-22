package org.grumpy.blog.justme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class SomeController {


    private SomeService someService;

    @Inject
    public SomeController(SomeService someService){
       this.someService = someService;
    }

    @RequestMapping(value = "/item/{id}", produces="application/json", method = RequestMethod.GET)
    public @ResponseBody String getItemDescription(@PathVariable("id") String id) {
        return someService.getItemDescription(id);
    }
}
