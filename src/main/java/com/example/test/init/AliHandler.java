package com.example.test.init;

import org.springframework.stereotype.Component;

/**
 * @Description
 */
@Component
public class AliHandler extends BaseHandler{

    public AliHandler() {
        type = MasterTypeEnum.ali;
    }
}
