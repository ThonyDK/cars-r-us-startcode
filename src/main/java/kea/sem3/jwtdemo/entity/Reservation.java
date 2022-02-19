package kea.sem3.jwtdemo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class Reservation {

    @Id
    //@GeneratedValue = Autogenerere et id til databasen
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    LocalDateTime reservationDate;

    LocalDate rentalDate;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedTo;

    public Reservation(LocalDate date, Car reservedCar, Member reservedTo) {
        this.rentalDate = date;
        this.reservedCar = reservedCar;
        this.reservedTo = reservedTo;
        reservedCar.addReservation(this);
        reservedTo.addReservation(this);
    }

    public Reservation() {
    }
}
