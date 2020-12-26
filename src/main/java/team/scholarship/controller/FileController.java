package team.scholarship.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.scholarship.result.Result;
import team.scholarship.result.StatusEnum;
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
public class FileController {

    @PostMapping("/upload")
    public Result upload(MultipartFile file, HttpServletRequest request) {

        if (file.isEmpty()) {
            return Result.ERROR(StatusEnum.NO_DATA);
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/software/tomcat9/webapps/奖学金申请材料/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            String url = "http://" + request.getServerName() + ":" +
                    request.getServerPort() + "/" +
                    request.getContextPath() + "/upload/" + fileName;
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ERROR(StatusEnum.NO_DATA);
    }
}
