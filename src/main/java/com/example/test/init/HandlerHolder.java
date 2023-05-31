package com.example.test.init;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Description
 */
@Component
public class HandlerHolder {

    private HashMap<MasterTypeEnum, BaseHandler> handlers = new HashMap<>(128);

    public void addHandler(MasterTypeEnum type, BaseHandler handler) {
        handlers.put(type, handler);
    }

}
