package com.absencemanger.absencemanger.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SearchDTO {
    String searchBy;
    String searchValue;
    LocalDate startDate;
    LocalDate endDate;
}
