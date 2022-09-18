package com.example.demoSkSearch.controller;

import com.example.demoSkSearch.response.BasicResponse;
import com.example.demoSkSearch.service.KeywordSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KeywordSearchController {
    @Autowired
    private KeywordSearchService keywordSearchService;

    @RequestMapping(value="/v1/search/keyword", method = RequestMethod.GET)
    public BasicResponse getKeywordSearch(Integer size){
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("size", size);

        BasicResponse resBody = keywordSearchService.getKeywordSearch(reqBody);

        return resBody;
    }
}
