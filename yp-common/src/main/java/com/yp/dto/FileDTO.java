package com.yp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @Classname
 * @Description 活动附件
 * @Author hcs
 * @Date 20200217
 * @Version 1.0
 */
@ApiModel(value = "文件上传DTO")
public class FileDTO {


    @NotNull
    @ApiModelProperty(value = "文件类型 0:活动banner 1:活动附件 2：示例 3:其他文件")
    private String fileType;
    @NotNull
    @ApiModelProperty(value = "文档内容;输出文件流")
    private MultipartFile file;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
