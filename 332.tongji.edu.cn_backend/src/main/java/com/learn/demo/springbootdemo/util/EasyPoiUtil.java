package com.learn.demo.springbootdemo.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author Administrator
 */
public class EasyPoiUtil {
    private static final Logger log = LoggerFactory.getLogger(EasyPoiUtil.class);

    public static void exportExcel(HttpServletResponse response,List list,Class c,String name) {
        log.info("请求 exportExcel start ......");


        try {
            // 设置响应输出的头类型及下载文件的默认名称
            String fileName = new String(name.getBytes("utf-8"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel;charset=gb2312");

            //导出
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), c, list);
            workbook.write(response.getOutputStream());
            log.info("请求 exportExcel end ......");
        } catch (IOException e) {
            log.info("请求 exportExcel 异常：{}", e.getMessage());
        }
    }

    public static List importExcel(MultipartFile file,Class c) {
        try {
            // 没有使用实体类注解的形式，这里用的Map
            List<Object> list = ExcelImportUtil.importExcel(
                    MultipartFileToFile.multipartFileToFile(file),
                    c,
                    new ImportParams()
            );
            return list;
        } catch (Exception e) {
            log.info(" Excel 导入异常：{}", e.getMessage());
        }
        return null;
    }
}
