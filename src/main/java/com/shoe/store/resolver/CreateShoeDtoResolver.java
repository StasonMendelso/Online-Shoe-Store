package com.shoe.store.resolver;

import com.shoe.store.dto.shoe.CreateShoeCardDto;
import com.shoe.store.dto.shoe.ShoeSizeDto;
import com.shoe.store.resolver.annotation.CreateShoeDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
public class CreateShoeDtoResolver implements HandlerMethodArgumentResolver {
    private final ApplicationContext applicationContext;
    private ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CreateShoeDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        if (servletModelAttributeMethodProcessor == null) {
            RequestMappingHandlerAdapter bean = applicationContext.getBean(RequestMappingHandlerAdapter.class);
            Optional<HandlerMethodArgumentResolver> servletModelAttributeResolver = bean.getArgumentResolvers()
                    .stream()
                    .filter(resolver -> resolver.getClass().equals(ServletModelAttributeMethodProcessor.class))
                    .findFirst();
            if (servletModelAttributeResolver.isEmpty()) {
                throw new RuntimeException("Can't resolve arguments without servletModelAttributeResolver");
            }
            this.servletModelAttributeMethodProcessor = (ServletModelAttributeMethodProcessor) servletModelAttributeResolver.get();
        }
        CreateShoeCardDto createShoeCardDto = (CreateShoeCardDto) servletModelAttributeMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String[] sizes = request.getParameterValues("size");
        String[] quantity = request.getParameterValues("quantity");
        List<ShoeSizeDto> shoeSizeDtoList = new ArrayList<>();

        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i].isBlank()) {
                continue;
            }
            shoeSizeDtoList.add(ShoeSizeDto.builder()
                    .size(sizes[i])
                    .quantity(quantity[i])
                    .build());
        }
        createShoeCardDto.setShoeSizeList(shoeSizeDtoList);

        return createShoeCardDto;
    }
}
