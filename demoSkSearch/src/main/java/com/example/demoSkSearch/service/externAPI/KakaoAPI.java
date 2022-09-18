package com.example.demoSkSearch.service.externAPI;

import com.example.demoSkSearch.vo.KakaoResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class KakaoAPI {

    private static String kakao_apikey = "e35aaf04fd075d1f565e37230d10aa5a";


    public static KakaoResponseDTO getBlogSearch(String query, String sort, Integer page, Integer size) throws RestClientException {

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakao_apikey);

        RestTemplate restTemplate = new RestTemplate();

        String apiURL = "https://dapi.kakao.com/v2/search/blog?" +
                "query=" + query;
        if(sort != null && sort.isEmpty() == false){
            apiURL = apiURL + "&sort=" + sort;
        }
        if(page != null){
            apiURL = apiURL + "&page=" + page;
        }
        if(size != null){
            apiURL = apiURL + "&size=" + size;
        }

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(apiURL, HttpMethod.GET, entity,KakaoResponseDTO.class).getBody();
    }
}
