package com.car.management.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private String type;
    private String model;
    private String color;
    private Integer speed;
    
    public Car() {}
   
	public Car(Integer id, String type, String model, String color, Integer speed) {
		super();
		this.id = id;
		this.type = type;
		this.model = model;
		this.color = color;
		this.speed = speed;
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Car [type=" + type + ", model=" + model + ", color=" + color + ", speed=" + speed + "]";
	}
	
	
}
