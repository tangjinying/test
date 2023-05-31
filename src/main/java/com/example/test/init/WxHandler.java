package com.example.test.init;

import org.springframework.stereotype.Component;

/**
 * @Description
 */
@Component
public class WxHandler extends BaseHandler{

    public WxHandler() {
        type = MasterTypeEnum.wx;
    }
}
