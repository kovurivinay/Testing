package com.Ticketing.Theatre.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ticketing.Theatre.Entity.Shows;

@Repository
public interface ShowsDAO extends JpaRepository<Shows, Integer> {

	Optional<Shows> findByShowName(String showName);

	void deleteByShowName(String showName);

}

