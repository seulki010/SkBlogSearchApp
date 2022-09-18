package com.example.demoSkSearch.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchKeyword {
    @Id
    private String keyword;
    private long hitcnt;

    @Version
    private Integer version;
}
