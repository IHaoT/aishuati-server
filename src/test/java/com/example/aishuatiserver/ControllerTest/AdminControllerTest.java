package com.example.aishuatiserver.ControllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AdminControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
        session = new MockHttpSession();
    }

    /**
     * @throws Exception
     */
    @Test
    public void testAdminLogin() throws Exception{
        RequestBuilder request = null;
        String Json = "{\n" +
                "    \"headers\": {\n" +
                "        \"Content-Type\": \"text/html;charset=utf-8\"\n" +
                "      },\n" +
                "    \"adminAccount\":\"admin\",\n" +
                "    \"adminPwd\":\"admin\"\n" +
                "}";
        request = MockMvcRequestBuilders.post("https://aishuati.zuccacm.top/admin/login").content(Json).contentType(MediaType.APPLICATION_JSON).session(session);  //传递参数类型指定为Json
        ResultActions resultActions = mvc.perform(request)
                .andExpect(status().isOk());
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        response.setCharacterEncoding("UTF-8");
        resultActions.andDo(print());

        /**
         * 测试获取学生信息
         */
        request = MockMvcRequestBuilders.post("https://aishuati.zuccacm.top/admin/getAllAdminInfo/1/10").session(session);
        resultActions = mvc.perform(request)
                .andExpect(status().isOk());
        response = resultActions.andReturn().getResponse();
        response.setCharacterEncoding("UTF-8");
        resultActions.andDo(print());

    }

}
