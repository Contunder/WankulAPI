package com.contunder.wankulapi.Data.Payload;

import com.contunder.wankulapi.Application.Model.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponse {

    private List<Collection> collections;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
