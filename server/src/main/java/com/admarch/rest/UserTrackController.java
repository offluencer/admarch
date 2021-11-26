package com.admarch.rest;


import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/admarch/")
public class UserTrackController {

    // let's work without backend for now
    // later let's add that as well

    // need to write the bussiness logic for this - but this is working so good
    Set<String> cookies = new HashSet<>();

    @RequestMapping(method = RequestMethod.GET, value = "/{version:[v|V][0-9]+}/user-track")
    public RedirectView trackUser(@CookieValue(value = "id", defaultValue = "") String id,
                            HttpServletResponse response,
                            @RequestParam(value = "influencer_regNum") String influencerRegNum,
                            @RequestParam(value = "redirectURL") String redirectURL) {
        // check if the id is present
        // flow: check if id is present, if not present set the cooking and respond
        if(StringUtils.isEmpty(id)){
            String cookie = UUID.randomUUID().toString();
            Cookie newCookie = new Cookie("id", cookie);
            newCookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
            response.addCookie(newCookie);

            // add to cookies
            cookies.add(cookie);
            System.out.println("new cookie - "+cookie);
        }else{
            // check if cookies exists in the list then do not increment
            // later is the business logic so we can do this
            System.out.println("existing cookie - "+id);
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://www.yahoo.com");
        return redirectView;
    }
}
