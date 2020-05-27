package com.yp.common.utils;

import com.yp.dto.FileDTO;
import com.yp.dto.UploadFileDTO;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletInputStream;
import java.io.*;
import java.lang.reflect.Method;

/**
 * @Classname IPUtil
 * @Description IP工具类
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019/12/14 10:19 下午
 * @Version 1.0
 */
public class FileUtil {

    /**
     *
     * @param fileDTO
     * @param filePath 文件存放路径
     * @return
     */
    public static String upload(UploadFileDTO fileDTO,String filePath) {
        InputStream sis = null;
        FileOutputStream fos = null;
        try {

            File file = new File("/data/"+filePath+fileDTO.getFile().getOriginalFilename());
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }

            sis = fileDTO.getFile().getInputStream();
            fos = new FileOutputStream(file);
            byte[] content = new byte[1024];
            int len = 0;
            while((len=sis.read(content)) > -1) {
                fos.write(content, 0, len);
            }
            fos.flush();
            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "fail";
        } finally {
            try {
                if(sis!=null) {
                    sis.close();
                }
                if(fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param fileDTO
     * @param filePath 文件存放路径
     * @return
     */
    public static String upload(FileDTO fileDTO, String filePath,String filename,String original) {
        InputStream sis = null;
        FileOutputStream fos = null;
        try {
            File file = new File("/data/"+filePath+filename+"."+original);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }

            sis = fileDTO.getFile().getInputStream();
            fos = new FileOutputStream(file);
            byte[] content = new byte[1024];
            int len = 0;
            while((len=sis.read(content)) > -1) {
                fos.write(content, 0, len);
            }
            fos.flush();
            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "fail";
        } finally {
            try {
                if(sis!=null) {
                    sis.close();
                }
                if(fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
