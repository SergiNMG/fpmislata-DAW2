package com.fpmislata.movies.http_response;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Response {

    private Object data;
    private Integer total_records;
    private Integer page;
    private Integer page_size;
    private Integer total_pages;
    private String next;
    private String previous;


    public Response(Object data, int total_records, Optional<Integer> page, int page_size) {
        this.data = data;
        this.total_records = total_records;
        if(page.isPresent()){
            buildPaginationMetaData(total_records, page_size, page.get());
        }
    }

    private void buildPaginationMetaData(int total_records, int page_size, int page){
        this.page = page;
        this.page_size = page_size;
        int total_pages = (int) (Math.ceil((double) total_records / page_size));
        this.total_pages = total_pages;

        if(page > 1 && total_pages > 1){
            this.previous = "/movies?page=" + (page - 1);
        }
        if (page < total_pages){
            this.next = "/movies?page=" + (page + 1);
        }
    }
}
