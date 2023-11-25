package com.expedia.demo.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Builder;
import lombok.Getter;


@JsonFilter("FilterBean")
@Builder
@Getter
public class FilterBean {
	private String field1;
	
	private String field2;
	private String field3;

	@Override
	public String toString() {
		return "FilterBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}

}
