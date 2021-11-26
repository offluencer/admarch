package com.admarch.rest;

import com.admarch.dao.QRCodeInfoRepository;
import com.admarch.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = "/admarch/")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @Autowired
    private QRCodeInfoRepository qrCodeInfoRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{version:[v|V][0-9]+}/user-action")
    public RedirectView trackUserAction(@CookieValue(value = "admarch-user-id", defaultValue = "") String admarchUserId,
                                  HttpServletResponse response, HttpServletRequest request,
                                  @RequestParam(value = "qr-code-id") String qrCodeId
                                  ){

        if(StringUtils.isEmpty(admarchUserId))
            admarchUserId = setAdmarchUserId(response);

        // track user action
        actionService.trackUserAction(admarchUserId,qrCodeId);

        // get re-direction url
        String redirectURL = qrCodeInfoRepository.findById(qrCodeId).getUrl();

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectURL);
        return redirectView;
    }

    private String setAdmarchUserId(HttpServletResponse response){
        String admarchUserId = UUID.randomUUID().toString();
        Cookie newCookie = new Cookie("admarch-user-id", admarchUserId);
        newCookie.setMaxAge(10 * 365 * 24 * 60 * 60); // expires in 10 years
        response.addCookie(newCookie);
        return admarchUserId;
    }
}
