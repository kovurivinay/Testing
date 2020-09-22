package com.Ticketing.Theatre.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "THEATRE")
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MOVIENAME")
	private String movieName;
	
	@OneToMany(mappedBy = "theatre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Shows> Shows;
	
	@Column(name = "LANGUAGE")
	private String language;
	
	@Column(name = "THEATRENAME")
	private Integer theatreName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public List<Shows> getShows() {
		return Shows;
	}

	public void setShows(List<Shows> shows) {
		Shows = shows;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(Integer theatreName) {
		this.theatreName = theatreName;
	}
	

}
