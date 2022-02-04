package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//JpaRepository er en udviddet udgave af CrudRepository som har lidt flere fetures
//Den skal vi derfor bruge i stedet for.
//Ved at venstreklikke og trykke view og quick definition kan man se alt det som JpaRepository kan.
public interface CarRepository extends JpaRepository<Cars, String> {

}
