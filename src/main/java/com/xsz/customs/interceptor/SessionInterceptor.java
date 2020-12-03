package com.xsz.customs.interceptor;

import com.xsz.customs.mapper.dcUserMapper;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

//继承了接口必须要实现其三个方法
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private dcUserMapper userMapper;

    //就是在进入一切url之前都需要判断用户是否已经登录，如果未登录则在session中不会出现我们传入的User对象
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("gqdm")){
                    String gqdm = cookie.getValue();
                    dcUserExample userexample = new dcUserExample();
                    userexample.createCriteria()
                            .andDcGqdmEqualTo(gqdm);
                    List<dcUser> users =  userMapper.selectByExample(userexample);
                    if (users.size() != 0){
                        HttpSession session = request.getSession();
                        session.setAttribute("user",users.get(0));
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
