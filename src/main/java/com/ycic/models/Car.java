package com.ycic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="cars")
//@EntityListeners(AuditEntityListener.class)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="car_name", nullable = false)
    private String carName;

    @Column(name="doors", nullable = false)
    private int doors;

    public Car() {
    }

    public Car(long id, String carName, int doors) {
        this.id = id;
        this.carName = carName;
        this.doors = doors;
    }

    public long getId() {
        return id;
    }

    public String getCarName() {
        return carName;
    }

    public int getDoors() {
        return doors;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", doors=" + doors +
                '}';
    }
}

