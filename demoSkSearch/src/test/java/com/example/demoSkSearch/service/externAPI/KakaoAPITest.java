package com.example.demoSkSearch.service.externAPI;

import com.example.demoSkSearch.vo.KakaoResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KakaoAPITest {
    @Test
    public void getBlogSearch_p(){
        KakaoResponseDTO resp = null;
        try {
            resp = KakaoAPI.getBlogSearch("test", "accuracy", 1, 1);
        }catch(Exception e){e.printStackTrace();}

        assertEquals(true, resp != null);
        assertEquals(true, resp.getDocuments().length == 1);
    }
    @Test
    public void getBlogSearch_n1(){
        KakaoResponseDTO resp = null;
        try{
            resp = KakaoAPI.getBlogSearch("test","test",1,1);
        }catch (Exception e){e.printStackTrace();}

        assertEquals(true, resp != null);
    }
    @Test
    public void getBlogSearch_n2(){
        KakaoResponseDTO resp = null;
        try{
            resp = KakaoAPI.getBlogSearch("test","accuracy",0,1);
        }catch (Exception e){e.printStackTrace();}

        assertEquals(true, resp == null);
    }
    @Test
    public void getBlogSearch_n3(){
        KakaoResponseDTO resp = null;
        try{
            resp = KakaoAPI.getBlogSearch("test","accuracy",1,51);
        }catch (Exception e){e.printStackTrace();}

        assertEquals(true, resp == null);
    }
}
