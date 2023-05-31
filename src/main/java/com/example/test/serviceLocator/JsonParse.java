package com.example.test.serviceLocator;

import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class JsonParse implements Parse {

    @Override
    public void parse() {
        System.out.println("json parse");
    }
}
