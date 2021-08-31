package com.example.project05.model.tour;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.project05.model.user.User;

@Entity
public class TourFav {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int favId;
	
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	@JoinColumn(name = "tourId")
	@ManyToOne
	private Tour tour;
}
