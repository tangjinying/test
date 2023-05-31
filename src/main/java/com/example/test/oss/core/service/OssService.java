package com.example.test.oss.core.service;

import com.example.test.oss.common.enums.FunctionTypeEnum;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {


    //上传文件到服务器
    String uploadingServer(MultipartFile file, FunctionTypeEnum type, Long relateId, String suffix);

    //删除服务器文件
    void deleteServerFile(String fileName);
}
