package com.example.demoSkSearch.controller;

import com.example.demoSkSearch.DemoSkSearchApplication;
import com.example.demoSkSearch.response.BasicResponse;
import com.example.demoSkSearch.vo.KakaoResponseDTO;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoSkSearchApplication.class)
public class BlogSearchControllerTest {
    @Autowired
    private BlogSearchController tObj;
    @Test
    public void getBlogSearch_p(){
        BasicResponse resp  = tObj.getBlogSearch("test","accuracy",1,1);

        Map<String, Object> resultMap = resp.getResult();

        assertEquals(true, resultMap != null);

        String source = (String)resultMap.get("source");
        KakaoResponseDTO body = (KakaoResponseDTO) resultMap.get("body");


        assertEquals(true, source != null);
        assertEquals(true, source == "kakao" || source == "naver");
        assertEquals(true, body != null);
        assertEquals(true, body.getDocuments().length == 1);


    }
    @Test
    public void getBlogSearch_n1(){

        BasicResponse resp  = tObj.getBlogSearch("test","test",1,1);

        assertEquals(true, resp != null);
        assertEquals(true, resp.getStatus().equals(BasicResponse.OK));
    }

    @Test
    public void getBlogSearch_n2(){

        BasicResponse resp  = tObj.getBlogSearch("test","accuracy",51,1);

        assertEquals(true, resp != null);
        assertEquals(true, resp.getStatus().equals(BasicResponse.NOK));
    }

    @Test
    public void getBlogSearch_n3(){

        BasicResponse resp  = tObj.getBlogSearch("test","accuracy",1,0);

        assertEquals(true, resp != null);
        assertEquals(true, resp.getStatus().equals(BasicResponse.NOK));
    }

}
