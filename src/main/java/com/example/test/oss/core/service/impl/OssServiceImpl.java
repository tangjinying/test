package com.example.test.oss.core.service.impl;

import cn.hutool.core.util.HashUtil;
import com.example.test.oss.common.consts.OssConsts;
import com.example.test.oss.common.enums.FunctionTypeEnum;
import com.example.test.oss.core.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class OssServiceImpl implements OssService {


    private final static String accessId = "yn97W0LE7Imfl8w0";
    private final static String bucketName = "dzb-familydoctor";
    private final static String accessKey = "t51ghAXEeo4e5UmuD8OSbORIHu7MNl";
    private final static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com"; //外网端点
    // private final static String endpoint = "https://dzb-familydoctor.oss-cn-hangzhou-internal.aliyuncs.com"; //内网端点




    @Override
    public String uploadingServer(MultipartFile file, FunctionTypeEnum type, Long relateId, String suffix) {
        //如果文件夹不存在，创建
        File filePath = new File(OssConsts.UPLOAD_PATH);
        if (!filePath.isDirectory()) {
            //递归生成文件夹
            boolean mkdirs = filePath.mkdirs();
            if(!mkdirs){
                throw new RuntimeException("创建文件夹失败");
            }
        }

        String fileName = type + "_" + relateId;
        int fileNameHash = HashUtil.cityHash32(fileName.getBytes(StandardCharsets.UTF_8));
        if(fileNameHash < 0) {//如果hash值小于0，取反
            fileNameHash = -fileNameHash;
        }
        fileName = fileNameHash + "." + suffix;
        try {
            file.transferTo(new File(filePath,fileName));
        } catch (Exception e) {
            throw new RuntimeException("上传文件失败");
        }
        return fileName;
    }


    @Override
    public void deleteServerFile(String fileName) {
        //文件
        File file = new File(OssConsts.UPLOAD_PATH, fileName);
        //如果文件存在，删除
        if (file.isFile() && file.exists()) {
            boolean delete = file.delete();
            if(!delete){
                throw new RuntimeException("删除文件失败");
            }
        }
    }

}
