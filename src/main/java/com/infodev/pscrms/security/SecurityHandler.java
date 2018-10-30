package com.infodev.pscrms.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@Component
public class SecurityHandler implements AuthenticationSuccessHandler {


    @Value("sct.user")
    String user1;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(authorities.contains("ADMIN_PRIVILEGE")){
            response.sendRedirect(request.getContextPath()+"/admin");

        }else{
            response.sendRedirect("/login");
        }

    }
}
