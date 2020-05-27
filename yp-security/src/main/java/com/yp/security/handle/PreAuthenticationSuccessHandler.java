package com.yp.security.handle;

import com.yp.common.utils.R;
import com.yp.security.PreSecurityUser;
import com.yp.util.JwtUtil;
import com.yp.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname PreAuthenticationSuccessHandler
 * @Description 登录成功处理器
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-07-08 13:50
 * @Version 1.0
 */
@Component
public class PreAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof PreSecurityUser) {
            PreSecurityUser userDetail = (PreSecurityUser) authentication.getPrincipal();
            //存储认证信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成token
            String s = JwtUtil.generateToken(userDetail);
            SecurityUtil.writeJavaScript(R.ok(s), response);
        }
    }
}

