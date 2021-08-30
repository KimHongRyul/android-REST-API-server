package com.example.project05.model.festival;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FestivalCafe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cafeId;
	private String cafeName;
	private double cafeRating;
	
	@JoinColumn(name = "festivalId")
	@ManyToOne
	private Festival festival;
}
