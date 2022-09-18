package com.example.demoSkSearch.controller;

import com.example.demoSkSearch.response.BasicResponse;
import com.example.demoSkSearch.service.BlogSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class BlogSearchController {

    @Autowired
    private BlogSearchService blogSearchService;

    @RequestMapping(value="/v1/search/blog", method = RequestMethod.GET)
    public BasicResponse getBlogSearch(@RequestParam String query, String sort, Integer page, Integer size){

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("query", query);
        reqBody.put("sort", sort);
        reqBody.put("page", page);
        reqBody.put("size", size);

        BasicResponse resBody = blogSearchService.getBlogSearch(reqBody);


        return resBody;
    }
}
