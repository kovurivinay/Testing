package com.Ticketing.Theatre.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ticketing.Theatre.DAO.ShowsDAO;
import com.Ticketing.Theatre.Entity.Shows;

@Service
@Transactional
public class ShowsService {
	@Autowired
	ShowsDAO showsDao;
	
	public Optional<Shows> getShows(String showName) {
		return this.showsDao.findByShowName(showName);
	}

	public List<Shows> getAllShows() {
		return this.showsDao.findAll();
	}

	public Shows addShows(Shows show) {
		Shows addedShows = this.showsDao.save(show);
		return addedShows;
	}

	public void deleteShows(String showName) {
		this.showsDao.deleteByShowName(showName);
		
	}
	
}
