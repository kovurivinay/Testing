package com.Ticketing.Theatre.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.Ticketing.Theatre.DAO.ShowsDAO;
import com.Ticketing.Theatre.Entity.Shows;
import com.Ticketing.Theatre.Service.ShowsService;

@RestController
@RequestMapping("/")
@CrossOrigin("*") 
public class ShowsController {
	@Autowired
	ShowsService showsService;
	
	@Autowired
	ShowsDAO showsDao;
	
	@RequestMapping("/showsTest")
	public String home() {
		return "Hello from Shows Service";
	}
	
	@GetMapping("/shows/{showsName}")
	public ResponseEntity<Shows> getShows(@PathVariable String showsName) {
		try {
			Optional<Shows> shows = this.showsService.getShows(showsName);
			if (!shows.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(shows.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/shows")
	public ResponseEntity<List<Shows>> getAllShowss() {
		try {
			List<Shows> shows = this.showsService.getAllShows();
			return ResponseEntity.ok(shows);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/shows")
	public ResponseEntity<Shows> addNewShows(@RequestBody Shows shows, @RequestParam("Role") String role) {
		try {
			if(role.equalsIgnoreCase("admin")){
				return ResponseEntity.ok(this.showsService.addShows(shows));
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	

	@PutMapping("/shows/{showsName}")
	public ResponseEntity<Shows> updateShows(@RequestBody Shows shows, @RequestParam("Role") String role) {
		try {
			if(role.equalsIgnoreCase("admin")){
				this.showsService.addShows(shows);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/shows/{showsName}")
	public ResponseEntity<Shows> deleteShows(@PathVariable String showsName, @RequestParam("Role") String role) {
		try {
			if(role.equalsIgnoreCase("admin")){
				this.showsService.deleteShows(showsName);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
