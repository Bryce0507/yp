package com.yp.manage.controller;

import com.yp.common.utils.FileUtil;
import com.yp.common.utils.R;
import com.yp.dto.FileDTO;
import com.yp.dto.UploadFileDTO;
import com.yp.dto.manage.notice.NoticeDTO;
import com.yp.entity.Activity;
import com.yp.service.ActivityService;
import com.yp.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Classname FileController
 * @Description 文件
 * @Author HCS lihaodongmail@163.com
 * @Date 2019-05-07 12:38
 * @Version 1.0
 */
@Api(tags = "文件")
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${file.address}")
    private String address;
    @ApiOperation(value = "上传销售凭证")
    @PostMapping("/uploadFile")
    public R uploadFile(@Valid FileDTO fileDTO) {
        String filePath;
        if(fileDTO.getFileType().equals("0")){
            //banner
            filePath="/activity/banner/";
        }else if(fileDTO.getFileType().equals("1")){
            //附件
            filePath="/activity/annex/";
        }else if(fileDTO.getFileType().equals("2")){
            //示例
            filePath="/activity/example/";
        }else{
            filePath="/activity/context/";
        }

        String[] file=fileDTO.getFile().getOriginalFilename().split("\\.");
        String fileName=file[0]+System.currentTimeMillis();
        String result= FileUtil.upload(fileDTO,filePath,fileName,file[1]);
        if(result.equals("success")){
            return R.ok(address+filePath+fileName+"."+file[1]);
        }else{
            return R.error("文件上传失败");
        }
    }

}
