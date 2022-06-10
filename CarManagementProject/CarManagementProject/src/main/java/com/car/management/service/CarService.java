package com.car.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.car.management.domain.Car;
import com.car.management.repo.CarRepository;

@Service
public class CarService {

private CarRepository carRepository;
	
	@Autowired
	public CarService(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
	}
	
	public Car createCar(Car car) {
		Car createdCar = this.carRepository.save(car);
		return createdCar;
	}

	public void removeCar(@PathVariable Integer id) {
		this.carRepository.deleteById(id);
	}
	
	
	public List<Car> getAllCars() {
		return this.carRepository.findAll();
	}
	
	public Car getCarById(Integer id) {
		Optional<Car> found = this.carRepository.findById(id);
		return found.get();
	}
	
	public Car replaceCarProperty(Integer id, Car newCarProperty) {
		Car existingCar = this.carRepository.findById(id).get();
		
		existingCar.setType(newCarProperty.getType());
		existingCar.setModel(newCarProperty.getModel());
		existingCar.setColor(newCarProperty.getColor());
		existingCar.setSpeed(newCarProperty.getSpeed());
		
		Car updatedCar = this.carRepository.save(existingCar);
		return updatedCar;
	}
}
