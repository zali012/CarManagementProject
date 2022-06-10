package com.car.management.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car.management.domain.Car;
import com.car.management.service.CarService;


@RestController
public class CarController {

	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		super();
		this.carService = carService;
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<Car> createCar(@RequestBody Car car){
		Car created = this.carService.createCar(car);
		ResponseEntity<Car> response = new ResponseEntity<Car>(created,HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Car>> getAllCars(){
		return ResponseEntity.ok(this.carService.getAllCars());
	}
	
	@GetMapping("/get/{id}")
	public Car getCarById(@PathVariable Integer id) {
		 return this.carService.getCarById(id);
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<Car> replaceCarProperty(@PathVariable Integer id,@RequestBody Car newCar){
		Car car = this.carService.replaceCarProperty(id, newCar);
		ResponseEntity<Car> responseEntity = new ResponseEntity<Car>(car,HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeCar(@PathVariable Integer id){
		this.carService.removeCar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
