package com.learn.demo.springbootdemo.controller;

import com.learn.demo.springbootdemo.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author chenbin
 * @description: 文件上传
 * @version: V1.0
 */
@RestController
public class UploadController {


    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return Result.ERROR("上传失败，请选择文件");
        }

        String fileName = file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + "//src//main//resources//static//upload//";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            String url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/upload/" + fileName;
            return Result.SUCCESS("上传成功", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ERROR("上传失败！");
    }
}

