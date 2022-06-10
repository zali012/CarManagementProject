package com.car.management.repo;

import org.springframework.stereotype.Repository;

import com.car.management.domain.Car;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer>  {

}
