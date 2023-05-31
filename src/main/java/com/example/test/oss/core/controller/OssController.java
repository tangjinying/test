package com.example.test.oss.core.controller;

import com.example.test.oss.common.enums.FunctionTypeEnum;
import com.example.test.oss.core.service.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/oss")
public class OssController {

    @Resource
    private OssService ossService;



    @PostMapping("/uploadingServer")
    public String filesUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") FunctionTypeEnum type,
                            @RequestParam("relateId") Long relateId, @RequestParam("suffix") String suffix) {

        return ossService.uploadingServer(file, type, relateId, suffix);
    }



    @GetMapping("/deleteServerFile")
    public void deleteServerFile(String fileName) {
        ossService.deleteServerFile(fileName);
    }


}
