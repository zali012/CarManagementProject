package com.car.management.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.car.management.domain.Car;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:car-schema.sql", "classpath:car-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class CarControllerIntegrationTests {

	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Car testCar = new Car(null, "racing", "2021", "yellow",160);
		String testCarsAsJson= this.mapper.writeValueAsString(testCar);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testCarsAsJson);
		
		Car testCreatedCars = new Car(3, "racing","2021", "yellow",160);
		String testCreatedFruitsAsJSON = this.mapper.writeValueAsString(testCreatedCars);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedFruitsAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		List<Car> carsList = List.of(new Car(1, "racing","2021", "blue",220),new Car(2, "racing","2020", "blue",220));
		String json = this.mapper.writeValueAsString(carsList);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void getTest() throws Exception {
		RequestBuilder req = get("/get/1");
		String carsAsJsonStr = this.mapper.writeValueAsString(new Car(1, "racing","2021", "blue",220));
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(carsAsJsonStr);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	
	@Test
	void testReplace() throws Exception {
		Car testCar = new Car(null, "racing", "2021", "yellow",160);
		String testFruitsAsJson = this.mapper.writeValueAsString(testCar);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testFruitsAsJson);
		Car testCarCreated = new Car(1,"racing","2021", "yellow",160);
		String testCreatedCarsAsJSON = this.mapper.writeValueAsString(testCarCreated);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedCarsAsJSON);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
			
	}
	
	@Test
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
	}
}
