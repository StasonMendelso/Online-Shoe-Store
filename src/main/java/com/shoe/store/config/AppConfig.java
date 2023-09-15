package com.shoe.store.config;

import com.shoe.store.interceptor.EnumInterceptor;
import com.shoe.store.resolver.CreateShoeDtoResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new EnumInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CreateShoeDtoResolver(applicationContext));
    }

}
