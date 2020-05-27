package com.yp.manage;

import com.yp.manage.util.wx.MessageSendUtil;
import com.yp.manage.util.wx.dto.WxApprovalRequDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Test
    public void test(){
        WxApprovalRequDTO approvalRequDTO=new WxApprovalRequDTO();
        approvalRequDTO.setApproverStatus("驳回");
        approvalRequDTO.setOpenId("o76cd5ONC514nN27YFkpeCGHtAJs");
        approvalRequDTO.setDesc("您的业绩已审核完毕，请注意查看");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        approvalRequDTO.setDate(sdf.format(new Date()));
        approvalRequDTO.setReason("测试");
        try {
            MessageSendUtil.sendApprovalMessage(approvalRequDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
