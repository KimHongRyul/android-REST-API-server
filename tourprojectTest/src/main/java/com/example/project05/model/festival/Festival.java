package com.example.project05.model.festival;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Festival {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int festivalId;
	private String festivalTitle;
	private String festivalAddr;
	private String usageDay;
	private String festivalArea;
	private String homepage;
	private String thumb;
	private String traffic;
	private String lat;
	private String lng;
	
	@OneToMany(mappedBy = "festival")
	private List<FestivalFav> likes;
}
