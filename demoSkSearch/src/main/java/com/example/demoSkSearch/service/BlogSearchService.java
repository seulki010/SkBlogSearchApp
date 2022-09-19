package com.example.demoSkSearch.service;

import com.example.demoSkSearch.repository.SearchKeywordRepository;
import com.example.demoSkSearch.response.BasicResponse;
import com.example.demoSkSearch.service.externAPI.KakaoAPI;
import com.example.demoSkSearch.service.externAPI.NaverAPI;
import com.example.demoSkSearch.vo.KakaoResponseDTO;
import com.example.demoSkSearch.vo.NaverResponseDTO;
import com.example.demoSkSearch.vo.SearchKeyword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class BlogSearchService{
    @Autowired
    SearchKeywordRepository searchKeywordRepository;

    public BasicResponse getBlogSearch(Map<String, Object> reqBody){
        String query = (String)reqBody.get("query");
        String sort = (String)reqBody.get("sort");
        Integer page = (Integer) reqBody.get("page");
        Integer size = (Integer) reqBody.get("size");

        BasicResponse res = new BasicResponse();


        // error case
        if(sort != null &&
                (sort.equals("accuracy") == false && sort.equals("recency") == false) ){
            sort = "accuracy";
        }

        if(page != null &&
                (page.intValue() < 1 || page.intValue() > 50)) {
            res.setStatus(BasicResponse.NOK);
            res.setReason("page value is not acceptable: " + page.intValue() + ", require:[1-50]");
            return res;
        }

        if(size != null &&
                (size.intValue() < 1 || size.intValue() > 50)){
            res.setStatus(BasicResponse.NOK);
            res.setReason("size value is not acceptable: " + size.intValue() + ", require:[1-50]");
            return res;

        }

        // valid search op
        updateSearchKeyword(query);

        KakaoResponseDTO kakaoResp = null;
        NaverResponseDTO naverResp = null;

        try{
            kakaoResp = KakaoAPI.getBlogSearch(query,sort,page,size);
        }catch(RestClientException e){
            log.error("[kakao RestClientException]: ", e);
        }finally {

            if(kakaoResp == null){
                try {
                    naverResp = NaverAPI.getBlogSearch(query, sort, page, size);
                }catch(RestClientException e){
                    log.error("[naver RestClientException]: ", e);
                }
            }
        }

        // first kakao
        if(kakaoResp != null){
            res.setStatus(BasicResponse.OK);
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("source", "kakao");
            resMap.put("body", kakaoResp);
            res.setResult(resMap);

            return res;
        }
        // second naver
        if(naverResp != null){
            res.setStatus(BasicResponse.OK);
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("source", "naver");
            resMap.put("body", naverResp);
            res.setResult(resMap);

            return res;
        }

        log.error("[total response failed]: ");

        res.setStatus(BasicResponse.NOK);
        res.setReason("API Call is temporary failed. Try again later");
        Map<String, Object> resMap = new HashMap<>();
        res.setResult(resMap);

        return res;

    }

    @Transactional
    public void updateSearchKeyword(String query){
        Optional<SearchKeyword> updateKeyword = searchKeywordRepository.findById(query);

        updateKeyword.ifPresentOrElse(
                selectId ->{
                    selectId.setHitcnt(selectId.getHitcnt() + 1);
                    searchKeywordRepository.save(selectId);
                },
                ()->{
                    SearchKeyword searchKeyword = new SearchKeyword();
                    searchKeyword.setKeyword(query);
                    searchKeyword.setHitcnt(1);

                    searchKeywordRepository.save(searchKeyword);
                });

    }

}
