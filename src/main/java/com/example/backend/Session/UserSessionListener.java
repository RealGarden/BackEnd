package com.example.backend.Session;

import com.example.backend.entity.member.MemberSession;
import com.example.backend.service.LoginService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.example.backend.entity.member.MemberSession.MEMBER_SESSION_KEY;

@WebListener
public class UserSessionListener implements HttpSessionListener {

    private static final Logger log = LoggerFactory.getLogger(UserSessionListener.class);

    private final LoginService loginService;

    public UserSessionListener(final LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        log.info("Session Created, session ID : {}, time-out : {}",
                httpSession.getId(), httpSession.getMaxInactiveInterval());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        log.info("Session Destroyed, session ID : {}, user : {}",
                httpSession.getId(), httpSession.getAttribute(MEMBER_SESSION_KEY));
        logout(httpSession);
    }

    private void logout(HttpSession httpSession) {
        MemberSession memberSession = (MemberSession) httpSession.getAttribute(MEMBER_SESSION_KEY);
        if (memberSession != null) {
            loginService.logout(memberSession.getId());
        }
    }
}