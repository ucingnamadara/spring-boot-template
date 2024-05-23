package com.dana.springboottemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {

	Integer currentPage;

	long totalEntries;

	Integer totalPage;

	Integer nextPage;

	Integer previousPage;

}
