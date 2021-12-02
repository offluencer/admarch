package com.admarch.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/admarch/")
public class QRCodeController {

    @RequestMapping(method = RequestMethod.GET, value = "/{version:[v|V][0-9]+}/welcome")
    public ModelAndView welcomePage() {
        return new ModelAndView("welcome");
    }
}
