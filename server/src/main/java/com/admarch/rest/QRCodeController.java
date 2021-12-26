package com.admarch.rest;

import com.admarch.dao.QRCodeInfoRepository;
import com.admarch.model.Influencer;
import com.admarch.model.QRCodeInfo;
import com.admarch.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/admarch/")
public class QRCodeController {

    public static final int campaignId = 1; // TODO - this is only pilot

    @Autowired
    private QRCodeInfoRepository qrCodeInfoRepository;

    @Autowired
    private InfluencerService influencerService;

    @RequestMapping(method = RequestMethod.GET, value = "/{version:[v|V][0-9]+}/qrcode-tag-form")
    public ModelAndView showQRCodeForm() {
        return new ModelAndView("qrcodetagform");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{version:[v|V][0-9]+}/qrcode-tag")
    public String tagQRCode(@RequestParam("qrcode") String qrCode,
                              @RequestParam("regNo") String regNo,
                            @RequestParam("type") String type) {
        // save the qr code and registered number in database
        String response = null;
        String successResponse = "QR code: "+qrCode+ " has been tagged to registered number: "+regNo;
        String failureResponse = "Failed to tag the QR code";

        /*
            This is temporary.
         */

        try{
            // If the offluencer register number is not there add to the table
            if(influencerService.getInfluencerByregNo(regNo) == null){
                Influencer influencer = new Influencer();
                influencer.setRegNumber(regNo);
                influencer.setType(type);
                influencerService.createInfluencer(influencer);
            }

            qrCodeInfoRepository.save(constructQRCodeInfo(qrCode,regNo,campaignId));
            response = successResponse;
        }catch (Exception e){
            response = failureResponse;
        }
        return response;
    }

    private QRCodeInfo constructQRCodeInfo(String qrCode, String influencerRegNo, int campaignId){
        QRCodeInfo qrCodeInfo = new QRCodeInfo();
        qrCodeInfo.setCampaignId(campaignId);
        qrCodeInfo.setInfluencerRegNo(influencerRegNo);
        qrCodeInfo.setId(qrCode);
        return qrCodeInfo;
    }
}
