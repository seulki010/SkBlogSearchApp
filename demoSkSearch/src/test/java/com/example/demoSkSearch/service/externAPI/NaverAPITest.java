package com.example.demoSkSearch.service.externAPI;

import com.example.demoSkSearch.vo.NaverResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NaverAPITest {
    @Test
    public void getBlogSearch_p(){
        NaverResponseDTO resp = null;
        try {
            resp = NaverAPI.getBlogSearch("test", "accuracy", 1, 1);
        }catch(Exception e){e.printStackTrace();}

        assertEquals(true, resp != null);
        assertEquals(true, resp.getItems().length == 1);
    }

    @Test
    public void getBlogSearch_n1(){
        NaverResponseDTO resp = null;
        try{
            resp = NaverAPI.getBlogSearch("test","test",1,1);
        }catch (Exception e){e.printStackTrace();}

        assertEquals(true, resp != null);
    }
    @Test
    public void getBlogSearch_n2(){
        NaverResponseDTO resp = null;
        try{
            resp = NaverAPI.getBlogSearch("test","accuracy",0,1);
        }catch (Exception e){e.printStackTrace();}

        assertEquals(true, resp == null);
    }
    @Test
    public void getBlogSearch_n3(){
        NaverResponseDTO resp = null;
        try{
            resp = NaverAPI.getBlogSearch("test","accuracy",1,101);
        }catch (Exception e){e.printStackTrace();}

        assertEquals(true, resp == null);
    }
}
