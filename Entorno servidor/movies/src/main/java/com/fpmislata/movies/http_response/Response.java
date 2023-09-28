package com.fpmislata.movies.http_response;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Response {

    private Object data;
    private int totalRecords;
    private int page;
    private int page_size;
    private int total_pages;
    private int next;
    private int previous;


    public Response(Object data, Integer totalRecords, Optional<Integer> page, int page_size) {
        this.data = data;
        this.totalRecords = totalRecords;
        if(page.isPresent()){
            buildPaginationMetaData(totalRecords, page_size, page.get());
        }
    }

    public buildPaginationMetaData(int totalRecords, int page_size, int page){
        this.page = page;
        this.page_size = page_size;
    }
}
