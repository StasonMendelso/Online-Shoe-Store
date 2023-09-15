package com.shoe.store.interceptor;

import com.shoe.store.enums.ProductStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Stanislav Hlova
 */
public class EnumInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        if (modelAndView != null) {
            if (modelAndView.getStatus() == null || !modelAndView.getStatus().isSameCodeAs(HttpStatus.FOUND)) {
                return;
            }
            modelAndView.addObject("ProductStatus_IN_AVAILABLE", ProductStatus.IN_AVAILABLE);
            modelAndView.addObject("ProductStatus_NOT_IN_AVAILABLE", ProductStatus.NOT_IN_AVAILABLE);
        }
    }
}
