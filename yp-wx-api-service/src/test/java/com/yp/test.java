package com.yp;

import com.yp.util.wx.MessageSendUtil;
import com.yp.util.wx.dto.WxActivityApprovalDTO;
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
        //发送消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        WxActivityApprovalDTO wxActivityApprovalDTO=new WxActivityApprovalDTO();
        wxActivityApprovalDTO.setOpenId("o76cd5ONC514nN27YFkpeCGHtAJs");
        wxActivityApprovalDTO.setShopName("测试");
        wxActivityApprovalDTO.setApplyTime(sdf.format(new Date()));
        wxActivityApprovalDTO.setName("姓名");
        wxActivityApprovalDTO.setReminder("请登录壹号精养后台进行审核操作");
        try {
            MessageSendUtil.sendApprovalMessage(wxActivityApprovalDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
