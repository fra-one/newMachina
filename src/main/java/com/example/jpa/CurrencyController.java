package com.example.jpa;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//CRUD on DB
@RestController //@Controller
public class CurrencyController {
	@Autowired
	private CurrencyRepository currencyRepository;
	
	// Get All currencies
	@RequestMapping("/currencies") //@GetMapping  //localhost:8080/currencies
	public List<Currency> getAllNotes() {
	    return currencyRepository.findAll();
	}

	// Get a Single currencies
	@RequestMapping("/currencies/{id}") //@GetMapping //localhost:8080/currencies/12
	public ResponseEntity<Currency> getNoteById(@PathVariable(value = "id") Long realId) {
		Currency currency = currencyRepository.findOne(realId);
	    if(currency == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(currency);
	}
	
	// Create a new currencies
	@RequestMapping("/currency/new")//@PostMapping //localhost:8080/currency/new
	public Currency createNote(@Valid @RequestBody Currency currency) {
	    return currencyRepository.save(currency);
	}
	
	// Update a currencies
	@RequestMapping("/currency/modify/{id}")//@PutMapping //localhost:8080/currency/modify5
	public ResponseEntity<Currency> updateNote(@PathVariable(value = "id") Long realId, 
	                                       @Valid @RequestBody Currency noteDetails) {
		Currency currency = currencyRepository.findOne(realId);
	    if(currency == null) {
	        return ResponseEntity.notFound().build();
	    }
	    currency.setInEUR(noteDetails.getInEUR());
	    currency.setInUSD(noteDetails.getInUSD());

	    Currency updatedNote = currencyRepository.save(currency);
	    return ResponseEntity.ok(updatedNote);
	}
		
	// Delete a currencies
	@RequestMapping("/currency/delete/{id}")	//@DeleteMapping //localhost:8080/currency/delete/5
	public ResponseEntity<Currency> deleteNote(@PathVariable(value = "id") Long realId) {
		Currency currency = currencyRepository.findOne(realId);
	    if(currency == null) {
	        return ResponseEntity.notFound().build();
	    }
	    currencyRepository.delete(currency);
	    return ResponseEntity.ok().build();
	}
}