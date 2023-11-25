package com.expedia.demo.controller;

import java.util.Arrays;
import java.util.List;

import com.expedia.demo.model.FilterBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Controller
public class FilteringController {


	
	@GetMapping("/filtering") //field2
	public MappingJacksonValue filtering() {

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(FilterBean.builder().field1("value1")
				.field2("value2").field3("value3"));
		
		SimpleBeanPropertyFilter filter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mappingJacksonValue.setFilters(filters );
		
		
		return mappingJacksonValue;
	}

	@GetMapping("/filtering-list") //field2, field3
	public MappingJacksonValue filteringList() {
		List<FilterBean> list = Arrays.asList(FilterBean.builder().field1("value1").field2("value2").field3("value3").build(),
				FilterBean.builder().field1("value4").field2("value5").field3("value6").build());
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("FilterBean", filter );
		
		mappingJacksonValue.setFilters(filters );
		

		return mappingJacksonValue;
	}

}
