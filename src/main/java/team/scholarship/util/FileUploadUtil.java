package team.scholarship.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileUploadUtil
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/23 21:37
 */
public class FileUploadUtil {

    public static String upload(MultipartFile multipartFile) {
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储
        String filePath = "./file/" + multipartFile.getOriginalFilename();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
