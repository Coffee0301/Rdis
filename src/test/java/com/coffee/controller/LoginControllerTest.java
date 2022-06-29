package com.coffee.controller;

import com.coffee.service.IUserService;
import com.coffee.vo.LoginVo;
import com.coffee.vo.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private IUserService userService;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }
    /**
     * 超时
     * */
    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(3000), () -> {
            // Simulate task that takes more than 10 ms.
            mockMvc.perform(
                    post("/login/toLogin")
            );
        });
    }
    @Test
    void doLoginReturnHttpStatusOK() throws Exception{
        LoginVo loginVo=new LoginVo("13814541880","Aa.123456");

//        输入验证
        when().thenReturn(expectedLoginVo());
//        验证200
        mockMvc.perform(
                post("/login/doLogin")
                        //.param("loginVo", String.valueOf(loginVo))
                        .content(objectMapper.writeValueAsString(loginVo))
        ).andExpect(status().isOk());
    }
    @Test
    void whenNullValue_thenReturns400() throws Exception {
        LoginVo loginVo=new LoginVo("null","null");

        mockMvc.perform(
                post("/login/doLogin")
                        .content(objectMapper.writeValueAsString(loginVo))
        ).andExpect(status().isBadRequest());
    }


    @Test
    void toLoginReturnHttpStatusOK() throws Exception{
        mockMvc.perform(
                post("/login/toLogin")
        ).andExpect(status().isOk());
    }
//    定义loginVo对象
    private LoginVo expectedLoginVo(){
        return LoginVo.builder()
                .mobile("13814541880")
                .password("Aa.123456")
                .build();
    }


}