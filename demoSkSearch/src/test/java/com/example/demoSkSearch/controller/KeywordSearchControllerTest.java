package com.example.demoSkSearch.controller;

import com.example.demoSkSearch.DemoSkSearchApplication;
import com.example.demoSkSearch.response.BasicResponse;
import com.example.demoSkSearch.response.SearchKeywordResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoSkSearchApplication.class)
public class KeywordSearchControllerTest {
    @Autowired
    KeywordSearchController tObj;

    @Autowired
    BlogSearchController blogSearchController;

    @Test
    public void getKeywordSearch_p(){
        blogSearchController.getBlogSearch("test",null,null,null);
        blogSearchController.getBlogSearch("test",null,null,null);
        blogSearchController.getBlogSearch("testt",null,null,null);
        blogSearchController.getBlogSearch("testtt",null,null,null);
        blogSearchController.getBlogSearch("testttt",null,null,null);
        blogSearchController.getBlogSearch("testtttt",null,null,null);

        BasicResponse resp  = tObj.getKeywordSearch(1);

        Map<String, Object> resultMap = resp.getResult();

        assertEquals(true, resultMap != null);

        List<SearchKeywordResponse> bodyList = (List<SearchKeywordResponse>) resultMap.get("body");

        assertEquals(true, bodyList != null);
        assertEquals(true, bodyList.size() == 1);
        assertEquals(true, bodyList.get(0).getKeyword().equals("test") == true);
    }
    @Test
    public void getKeywordSearch_n(){
        BasicResponse resp  = tObj.getKeywordSearch(11);


        assertEquals(true, resp != null);
        assertEquals(true, resp.getStatus().equals(BasicResponse.NOK));
    }
}
