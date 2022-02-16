package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    //Kode lavet for at en bil ikke kan double bookes af to kunder på samme dato. Koden bruges over i MakeTestData
    Reservation findReservationByReservedCar_IdAndRentalDate(int id, LocalDate date);

}
