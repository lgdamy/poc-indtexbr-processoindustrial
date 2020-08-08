package com.indtexbr.processoindustrial.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultPage<T>  {
    private List<T> content;
    private long totalElements;
    private int page;
    private int pageSize;

    public ResultPage(List<T> content, Pageable pageable, long total) {
        super();
        this.content = content;
        this.totalElements = total;
        this.page = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();
    }

}
