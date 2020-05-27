package com.yp.dto.wx;

import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ApiModel(value = "修改密码")
public class WxChangePwdDTO {

    /**
     * 微信昵称
     */
    @NotNull
    @Length(min = 6, max = 12, message = "密码长度为6-12位")
    private String newPass;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
