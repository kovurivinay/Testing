package com.Ticketing.Booking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ticketing.Booking.DAO.BookingDAO;
import com.Ticketing.Booking.Entities.Booking;

@Service
@Transactional
public class BookingService {

	@Autowired
	BookingDAO bookingDao;
	
	public Optional<Booking> getBooking(String username) {
		return this.bookingDao.findBookingByUserName(username);
	}

	public List<Booking> getAllBookings() {
		return this.bookingDao.findAll();
	}

	public Booking addBooking(Booking booking) {
		return this.bookingDao.save(booking);
	}

	public void deleteBooking(String username) {
		this.bookingDao.deleteByUserName(username);
		
	}

	public void cancelBooking(String username, int bookingId) {
		// TODO Auto-generated method stub
		
	}

	public Optional<Booking> getBooking(String username, int bookingId) {
		return this.bookingDao.findBookingByUserNameAndId(username, bookingId);
		
	}

}