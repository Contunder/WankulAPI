package com.contunder.wankulapi.Application.Model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class Pageable {

    int pageNo;
    int pageSize;
    String sortBy;
    Sort sort;

    public Pageable(int pageNo, int pageSize, String sortBy, String sortDir) {
        this.sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
    }

    public org.springframework.data.domain.Pageable getPage(){
        return PageRequest.of(pageNo, pageSize, sort);
    }
}
