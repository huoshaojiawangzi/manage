package com.lizc.sports.common.utils.page;

import lombok.Data;

import java.util.List;

@Data
public class LocalPage<T> 
{
    private List<T> content;
    
    private long totalElements;
    
    public LocalPage (List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }
}
