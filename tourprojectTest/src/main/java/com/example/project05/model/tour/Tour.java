package com.example.project05.model.tour;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.project05.model.festival.Festival;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
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
	
	@OneToMany(mappedBy = "tour")
	private List<TourFav> likes;
	
}
