package com.example.demoSkSearch.service;

import com.example.demoSkSearch.repository.SearchKeywordRepository;
import com.example.demoSkSearch.response.BasicResponse;
import com.example.demoSkSearch.response.SearchKeywordResponse;
import com.example.demoSkSearch.vo.SearchKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeywordSearchService {
    @Autowired
    private SearchKeywordRepository searchKeywordRepository;

    @Transactional
    public BasicResponse getKeywordSearch(Map<String, Object> reqBody){
        Integer size = (Integer) reqBody.get("size");

        BasicResponse res = new BasicResponse();

        if(size != null &&
                (size.intValue() < 1 || size.intValue() > 10)){
            res.setStatus(BasicResponse.NOK);
            res.setReason("size value is not acceptable: " + size.intValue() + ", require:[1-10]");
            return res;
        }

        if(size == null){
            size = 10;
        }

        List<SearchKeyword> keywordList = searchKeywordRepository.findAlLByOrderByHitcntDesc(PageRequest.of(0, size.intValue()));
        List<SearchKeywordResponse> respList = new ArrayList<>();

        keywordList.forEach(item ->{
            SearchKeywordResponse respItem = new SearchKeywordResponse();
            respItem.setKeyword(item.getKeyword());
            respItem.setHitcnt(item.getHitcnt());

            respList.add(respItem);
        });

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("body", respList);

        res.setStatus(BasicResponse.OK);
        res.setResult(resMap);
        return res;
    }
}
