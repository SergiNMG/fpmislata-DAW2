package com.fpmislata.movies.http_response;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@Getter
@Setter
@JsonPropertyOrder({"total_records", "page", "page_size", "total_pages", "next", "previous", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("response")
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


    @JsonIgnore
    private String url = "http://localhost:3010/movies";

    @Value("${buildPagination.defaultPageSize}")
    private Integer page_size_default;


    public Response(Object data, int total_records, Optional<Integer> page, Optional<Integer> page_size) {
        this.data = data;
        this.total_records = total_records;
        //buildPaginationMetaData(total_records, page_size.get(), page.get());
        /*if(page.isPresent() && page_size.isPresent()){
            buildPaginationMetaData(total_records, page_size.get(), page.get());
        }
        else{
            buildPaginationMetaData(total_records, page_size_default, page.get());
        }*/
        if (page.isPresent()){
            buildPaginationMetaData(total_records, page_size.get(), page.get());
        }

    }

    private void buildPaginationMetaData(int total_records, int page_size, int page){

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
}
