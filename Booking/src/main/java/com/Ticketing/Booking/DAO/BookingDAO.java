package com.Ticketing.Booking.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ticketing.Booking.Entities.Booking;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Integer>{

	Optional<Booking> findBookingByUserName(String username);

	void deleteByUserName(String username);

	Optional<Booking> findBookingByUserNameAndId(String username, int bookingId);

}