package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class carRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @BeforeEach
    void setUp(@Autowired CarRepository carRepository) {
        carRepository.save(new Car("Mercedes-Benz", "A45S", 5000, 10));
        carRepository.save(new Car("Audi", "RS3", 4000, 10));
    }
    @Test
    public void testCount(){
        assertEquals(2, carRepository.count());
    }
}