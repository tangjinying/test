package com.example.test.callback;

import lombok.Data;

/**
 * @Description
 */
@Data
public class Producer {

    public String consumer(ResultCallback callback) {

        return callback.callback("成功");

    }



}
