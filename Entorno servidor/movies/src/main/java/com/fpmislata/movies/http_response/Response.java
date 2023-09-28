package com.fpmislata.movies.http_response;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Response {

    private Object data;
    private Integer totalRecords;
    private Integer page;
    private Integer page_size;
    private Integer total_pages;
    private String next;
    private String previous;


    public Response(Object data, int totalRecords, Optional<Integer> page, int page_size) {
        this.data = data;
        this.totalRecords = totalRecords;
        if(page.isPresent()){
            buildPaginationMetaData(totalRecords, page_size, page.get());
        }
    }

    private void buildPaginationMetaData(int totalRecords, int page_size, int page){
        this.page = page;
        this.page_size = page_size;
        int total_Pages = (int) (Math.ceil((double) totalRecords / page_size));
        this.total_pages = total_pages;

        if(page > 1 && total_pages > 1){
            this.previous = "/movies?page=" + (page - 1);
        }
        if (page < total_pages){
            this.next = "/movies?page=" + (page + 1);
        }
    }
}
