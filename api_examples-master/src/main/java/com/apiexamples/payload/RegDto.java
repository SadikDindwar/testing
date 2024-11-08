package com.apiexamples.payload;

import lombok.Data;

import java.util.List;

@Data
public class RegDto {

    //This class is used for sending List of Registration Dtos along with some extra information.

    private List<RegistrationDto> dto;
    private int totalPages;
    private boolean isLast;
    private boolean isFirst;
    private int pageSize;
    private int pageNumber;

    public RegDto(List<RegistrationDto> dto, int totalPages, boolean isLast, boolean isFirst, int pageSize, int pageNumber) {
        this.dto = dto;
        this.totalPages = totalPages;
        this.isLast = isLast;
        this.isFirst = isFirst;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
}
