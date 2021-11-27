package com.admarch.service;

import javax.servlet.http.HttpServletRequest;

public interface ActionService {
    void trackUserAction(String admarchUserId, String qrCodeId);
}
