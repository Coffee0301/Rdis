package com.coffee.service.impl;

import com.coffee.exception.GlobalException;
import com.coffee.mapper.UserMapper;
import com.coffee.vo.LoginVo;
import com.coffee.vo.RespBeanEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RedisTemplate redisTemplate=Mockito.mock(RedisTemplate.class);

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        this.userServiceImpl=new UserServiceImpl();
    }

    @Test
    void exceptionTesting() {
        LoginVo loginVo=new LoginVo(null,"Aa.123456");
//        when(userMapper.selectById(anyString())).thenReturn(null);
        doReturn(null).when(userMapper).selectById(anyString());
//        try {
////            when(userMapper.selectById(eq())).thenReturn(null);
//            UserServiceImpl userServiceSpy=spy(userServiceImpl);
//            assertNotNull(userServiceSpy.login(loginVo));
//
//        }catch (Exception e){
//            e.printStackTrace();
//            assertTrue(e instanceof GlobalException);
//
//        }

        UserServiceImpl userServiceSpy=spy(userServiceImpl);
        Exception exception = assertThrows(GlobalException.class, () ->
                userServiceSpy.login(loginVo));
        assertEquals(RespBeanEnum.LOGIN_ERROR, exception.getMessage());
    }

//    @Test
//    void whenNullValue_theReturnErrorResult()throws Exception{
//        LoginVo loginVo=new LoginVo("","Aa.123456");
////        MvcResult mvcResult=mockMvc.perform();
//        try {
//            when(userMapper.selectById(eq())).thenReturn(null);
//        }catch (Exception e){
//            assertTrue(e instanceof GlobalException);
//        }
//    }

    @Test
    void login() {
//        验证用户是否为空

        LoginVo loginVo=new LoginVo("13814541880","Aa.123456");
//        when()
        assertNotNull(loginVo);
        assertThat(loginVo).isNotNull();
//        验证错误是否抛出

//        验证密码是否正确

//        判断返回值是否为期望结果
    }

    private LoginVo expectedLoginVo(){
        return LoginVo.builder()
                .mobile("13814541880")
                .password("Aa.123456")
                .build();
    }
}