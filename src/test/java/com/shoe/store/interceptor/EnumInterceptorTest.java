package com.shoe.store.interceptor;

import com.shoe.store.BaseUnitTest;
import com.shoe.store.enums.ProductStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Stanislav Hlova
 */
class EnumInterceptorTest extends BaseUnitTest {
    private EnumInterceptor enumInterceptor = new EnumInterceptor();
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HandlerMethod handler;
    @Mock
    private ModelAndView modelAndView;

    @Test
    void shouldSetProductStatusEnumValuesInModel_whenModelNotNull() {

        enumInterceptor.postHandle(request, response, handler, modelAndView);

        verify(modelAndView, times(1)).addObject("ProductStatus_IN_AVAILABLE", ProductStatus.IN_AVAILABLE);
        verify(modelAndView, times(1)).addObject("ProductStatus_NOT_IN_AVAILABLE", ProductStatus.NOT_IN_AVAILABLE);
    }

    @Test
    void shouldNotSetValuesInModel_whenModelNull() {
        assertDoesNotThrow(() -> enumInterceptor.postHandle(request, response, handler, null));
    }
}