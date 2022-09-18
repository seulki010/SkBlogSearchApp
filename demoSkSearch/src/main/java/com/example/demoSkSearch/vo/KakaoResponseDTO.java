package com.example.demoSkSearch.vo;

import lombok.Data;


@Data
public class KakaoResponseDTO {
    public Meta meta;
    public Document[] documents;

    @Data
    public static class Meta{
        public Integer total_count;
        public Integer pageable_count;
        public Boolean is_end;
    }
    @Data
    public static class Document{
        public String title;
        public String contents;
        public String url;
        public String blogname;
        public String thumbnail;
        public String datetime;

    }
}
