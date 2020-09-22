package com.Ticketing.Booking.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ticketing.Booking.DAO.BookingDAO;
import com.Ticketing.Booking.Service.BookingService;
import com.Ticketing.Booking.Entities.Booking;

@RestController
@RequestMapping("/")
@CrossOrigin("*") 
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	BookingDAO bookingDao;
	
	@RequestMapping("/")
	public String home() {
		return "Hello from Booking Service";
	}
	
	@GetMapping("/booking/{username}")
	public ResponseEntity<Booking> getBooking(@PathVariable String username) {
		try {
			Optional<Booking> booking = this.bookingService.getBooking(username);
			if (!booking.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(booking.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/bookings")
	public ResponseEntity<List<Booking>> getAllBookings() {
		try {
			List<Booking> booking = this.bookingService.getAllBookings();
			return ResponseEntity.ok(booking);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/bookingsBetweenDates")
	public ResponseEntity<List<Booking>> getbookingsBetweenDates(@RequestParam("Role") String role, @RequestParam("startDate") String d1, @RequestParam("endDate") String d2) {
		try {
			if(role.equalsIgnoreCase("admin")){
			    Date startDate=new SimpleDateFormat("dd-MM-yyyy").parse(d1);  
			    Date endDate=new SimpleDateFormat("dd-MM-yyyy").parse(d2);  
				List<Booking> bookings = this.bookingService.getAllBookings();
				List<Booking> filteredBooking = new ArrayList<>();
				for(Booking b:bookings) {
					if (b.getDate().after(startDate) && b.getDate().before(endDate)) {
						filteredBooking.add(b);
					}
				}
				return ResponseEntity.ok(filteredBooking);
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/cancelledbookings")
	public ResponseEntity<List<Booking>> getCancelledBookings(@RequestParam("Role") String role, @RequestParam("startDate") String d1, @RequestParam("endDate") String d2) {
		try {
			if(role.equalsIgnoreCase("admin")){
			    Date startDate=new SimpleDateFormat("dd-MM-yyyy").parse(d1);  
			    Date endDate=new SimpleDateFormat("dd-MM-yyyy").parse(d2);  
				List<Booking> bookings = this.bookingService.getAllBookings();
				List<Booking> filteredBooking = new ArrayList<>();
				for(Booking b:bookings) {
					if (b.getCancelled() && b.getDate().after(startDate) && b.getDate().before(endDate)) {
						filteredBooking.add(b);
					}
				}
				return ResponseEntity.ok(filteredBooking);
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/booking")
	public ResponseEntity<Booking> addNewBooking(@RequestBody Booking booking, @RequestParam("Role") String role) {
		try {
			if(role.equalsIgnoreCase("admin")){
				return ResponseEntity.ok(this.bookingService.addBooking(booking));
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	

	@PutMapping("/booking/{username}")
	public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @RequestParam("Role") String role) {
		try {
			if(role.equalsIgnoreCase("admin")){
				this.bookingService.addBooking(booking);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/booking/{username}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable String username) {
		try {
			this.bookingService.deleteBooking(username);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/cancelBooking/{username}/{bookingId}")
	public ResponseEntity<Object> cancelBooking(@PathVariable String username, @PathVariable int bookingId) {
		try {
			Optional<Booking> retrievedBooking = this.bookingService.getBooking(username, bookingId);
			if(!retrievedBooking.isPresent()) {
				return new ResponseEntity<Object>("Booking not found", HttpStatus.NOT_FOUND);
			}
			Booking currentBooking = retrievedBooking.get();
			currentBooking.setCancelled(true);
			this.bookingDao.save(currentBooking);
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
