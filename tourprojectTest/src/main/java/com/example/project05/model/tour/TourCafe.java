package com.example.project05.model.tour;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TourCafe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cafeId;
	private String cafeName;
	private double cafeRating;
	
	@JoinColumn(name = "tourId")
	@ManyToOne
	private Tour tour;
}
