package com.yp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Classname
 * @Description 活动附件
 * @Author hcs
 * @Date 20200217
 * @Version 1.0
 */
@ApiModel(value = "文件上传DTO")
public class UploadFileDTO {

//    @NotNull
//    @ApiModelProperty(value = "文件名")
//    private String fileName;
//    @NotNull
//    @ApiModelProperty(value = "文件类型")
//    private String fileType;
    @NotNull
    @ApiModelProperty(value = "文档内容;输出文件流")
    private MultipartFile file;

//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getFileType() {
//        return fileType;
//    }
//
//    public void setFileType(String fileType) {
//        this.fileType = fileType;
//    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
