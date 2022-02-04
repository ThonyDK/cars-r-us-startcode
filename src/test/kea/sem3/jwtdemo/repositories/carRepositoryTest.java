package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Cars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class carRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @BeforeEach
    void setUp(@Autowired CarRepository carRepository) {
        carRepository.save(new Cars("Mercedes-Benz", "A45S", 5000));
        carRepository.save(new Cars("Audi", "RS3", 4000));
    }
    @Test
    public void testCount(){
        assertEquals(2, carRepository.count());
    }
}