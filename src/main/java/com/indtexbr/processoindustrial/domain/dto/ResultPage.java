package com.indtexbr.processoindustrial.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultPage<T> extends PageImpl<T> {
    private List<T> content;
    private long totalElements;
    private int page;
    private int pageSize;


    public ResultPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        this.content = content;
        this.totalElements = total;
        this.page = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();
    }
}
