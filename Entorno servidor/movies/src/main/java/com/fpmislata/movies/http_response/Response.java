package com.fpmislata.movies.http_response;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
//@JsonPropertyOrder({"total_records", "pagination", "data"})
@JsonPropertyOrder({"total_records", "page", "page_size", "total_pages", "next", "previous", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("response")
//@Builder
public class Response {


    private Object data;
    @JsonProperty("total records")
    private Integer total_records;
    private Integer page;
    @JsonProperty("page size")
    private Integer page_size;
    @JsonProperty("total pages")
    private Integer total_pages;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;

    @Value("${app.url}")
    private String url;

    @Value("${buildPagination.defaultPageSize}")
    private Integer page_size_default;

    public Response(Object data, int total_records, Optional<Integer> page, Optional<Integer> page_size, String url) {
        this.data = data;
        this.total_records = total_records;
        this.url = url;
        if (page.isPresent()){
            buildPaginationMetaData(total_records, page_size.get(), page.get(), url);
        }

    }

        private void buildPaginationMetaData(int total_records, int page_size, int page, String url){

        this.page = page;
        this.page_size = page_size;
        int total_pages = (int) (Math.ceil((double) total_records / page_size));
        this.total_pages = total_pages;

        if(page > 1 && total_pages > 1){
            this.previous = url + "?page=" + (page - 1);
        }
        if (page < total_pages){
            this.next = url + "?page=" + (page + 1);
        }
    }
    /*
    private Object data;

    private Integer total_records;

    @JsonProperty("PaginationData")
    private Map<String, Object> pagination;

    public void paginate(int page, int page_size, String url){
        this.pagination = new HashMap<>();
        this.pagination.put("page", page);
        this.pagination.put("page size", page_size);
        int total_pages = (int) (Math.ceil((double) total_records/page_size));
        this.pagination.put("total pages", total_pages);
        if(page > 1 && total_pages >1){
            this.pagination.put("previous", url + "/movies?page=" + (page - 1));
        }
        if(page < total_pages){
            this.pagination.put("next", url + "/movies?page=" + (page + 1));
        }
    }
    */
}
