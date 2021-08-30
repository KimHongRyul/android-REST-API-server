package com.example.project05.model.tour;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.project05.model.festival.Festival;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Tour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tourId;
	
	private String tourTitle;
	private String tourAddr;
	private String usageDay;
	private String tourArea;
	private String homepage;
	private String thumb;
	private String traffic;
	private String tel;
	private String lat;
	private String lng;
	
}
