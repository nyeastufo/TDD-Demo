package com.ycic;

import com.ycic.models.Car;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TdddemoApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TdddemoApplicationTests {
	final private static String HOST = "http://localhost:";
	final private static String APIBASEURI = "/api/v1";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getRootUrl() {
		return HOST  + port + APIBASEURI;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllCars() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/cars",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetCarById() {
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/1", Car.class);
		System.out.println(car.getCarName());
		Assert.assertNotNull(car);
	}

	@Test
	public void testCreateCar() {
		Car car = new Car();
		car.setCarName("Prius");
		car.setDoors(4);

		ResponseEntity<Car> postResponse = restTemplate.postForEntity(getRootUrl() + "/cars",
				car, Car.class);

		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateCar() {
		int id = 1;
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		car.setCarName("Tesla");
		car.setDoors(2);

		restTemplate.put(getRootUrl() + "/cars/" + id, car);

		Car updatedCar = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		Assert.assertNotNull(updatedCar);
	}
}
