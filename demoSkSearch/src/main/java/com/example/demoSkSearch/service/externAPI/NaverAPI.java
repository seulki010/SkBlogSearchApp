package com.example.demoSkSearch.service.externAPI;

import com.example.demoSkSearch.vo.NaverResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class NaverAPI {
    private static String naver_client_id = "_As96tqIlZtPLXrDtves";
    private static String naver_client_secret = "IC7ExnlMeP";

    public static NaverResponseDTO getBlogSearch(String query, String sort, Integer start, Integer display) throws RestClientException {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naver_client_id);
        headers.set("X-Naver-Client-Secret", naver_client_secret);

        RestTemplate restTemplate = new RestTemplate();

        String apiURL = "https://openapi.naver.com/v1/search/blog?" +
                "query=" + query;
        if(sort != null && sort.isEmpty() == false){
            if(sort.equals("accuracy") == true){
                apiURL = apiURL + "&sort=" + "sim";
            }else if(sort.equals("recency") == true){
                apiURL = apiURL + "&sort=" + "date";
            }
        }
        if(start != null){
            apiURL = apiURL + "&start=" + start;
        }
        if(display != null){
            apiURL = apiURL + "&display=" + display;
        }


        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(apiURL, HttpMethod.GET, entity, NaverResponseDTO.class).getBody();
    }
}
