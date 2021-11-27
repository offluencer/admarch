package com.admarch.service;

import com.admarch.dao.ActionsRepository;
import com.admarch.dao.QRCodeInfoRepository;
import com.admarch.dao.UserInfoRepository;
import com.admarch.model.Actions;
import com.admarch.model.QRCodeInfo;
import com.admarch.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
public class ActionServiceImpl implements ActionService {
    @Value("${application.action.conversion.earning}")
    private int conversionEarning;

    @Autowired
    private QRCodeInfoRepository qrCodeInfoRepository;

    @Autowired
    private ActionsRepository actionsRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private RequestService requestService;

    @Override
    public void trackUserAction(String admarchUserId, String qrCodeId,HttpServletRequest request) {

        QRCodeInfo qrCodeInfo = qrCodeInfoRepository.findById(qrCodeId);
        Actions actions = actionsRepository.findByregisterNumberAndDeviceIdAndCampaignId(qrCodeInfo.getInfluencerRegNo(),admarchUserId,qrCodeInfo.getCampaignId());

        if(actions==null)
            saveActionAndUserInfo(qrCodeInfo,admarchUserId,request);
    }

    private Actions constructActions(String regNo, String deviceId, int campaignId){
        Actions actions = new Actions();
        actions.setActionEarning(conversionEarning);
        actions.setActionDateTime(new Date());
        actions.setRegisterNumber(regNo);
        actions.setDeviceId(deviceId);
        actions.setCampaignId(campaignId);
        return actions;
    }

    private UserInfo constructUserInfo(String userId, HttpServletRequest request){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);

        String ipAddress = requestService.getClientIp(request);
        userInfo.setIpAddress(ipAddress);
        return userInfo;
    }

    private void saveActionAndUserInfo(QRCodeInfo qrCodeInfo, String admarchUserId,HttpServletRequest request){
        // populate action object
        Actions actions = constructActions(qrCodeInfo.getInfluencerRegNo(),admarchUserId,qrCodeInfo.getCampaignId());

        // populate user info object
        UserInfo userInfo = constructUserInfo(admarchUserId,request); // TODO - change the ip address

        // save action and user info
        // TODO : it has to be in a single transaction
        actionsRepository.save(actions);
        userInfoRepository.save(userInfo);
    }
}
