package com.example.project05.controller.dto;

import com.example.project05.model.festival.Festival;
import com.example.project05.model.tour.Tour;

import lombok.Data;

@Data
public class FestivalListDto {

	private int fest_id;
	private String title;
	private String thumb;
	
	public Festival toEntity() {
		return Festival.builder()
				.festivalId(fest_id)
				.festivalTitle(title)
				.thumb(thumb)
				.build();
	}
	
}
