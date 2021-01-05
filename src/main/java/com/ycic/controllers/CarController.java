package com.ycic.controllers;

import com.ycic.models.Car;
import com.ycic.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarsById(@PathVariable(value="id") Long carId)
        throws ResourceNotFoundException {
        Car car = carRepository
                .findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
        return ResponseEntity.ok().body(car);
    }

    @PostMapping("/cars")
    public Car createCar(@Valid @RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(
            @PathVariable(value="id") Long carId, @Valid @RequestBody Car carDetails)
            throws ResourceNotFoundException {
        Car car = carRepository
                .findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car " + carId + " not found"));

        car.setCarName(carDetails.getCarName());
        car.setDoors(carDetails.getDoors());

        final Car updatedCar = carRepository.save(car);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/car/{id}")
    public Map<String, Boolean> deleteCar(@PathVariable(value="id") Long carId)
        throws Exception {
        Car car = carRepository
                .findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car " + carId + " not found"));
        carRepository.delete(car);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
