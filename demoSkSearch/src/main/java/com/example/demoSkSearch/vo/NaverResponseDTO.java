package com.example.demoSkSearch.vo;

import lombok.Data;


@Data
public class NaverResponseDTO {
    public String lastBuildDate;
    public Integer total;
    public Integer start;
    public Integer display;
    public Item[] items;

    @Data
    public static class Item{
        public String title;
        public String link;
        public String description;
        public String bloggername;
        public String bloggerlink;
        public String postdate;
    }
}
