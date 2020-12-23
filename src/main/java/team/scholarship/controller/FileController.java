package team.scholarship.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.scholarship.result.Result;
import team.scholarship.util.FileUploadUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName FileController
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/23 21:31
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        // replaceAll 用来替换windows中的\\ 为 /
        return FileUploadUtil.upload(file).replaceAll("\\\\", "/");
    }
}
