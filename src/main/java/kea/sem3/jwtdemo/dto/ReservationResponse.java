package kea.sem3.jwtdemo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    int id;
    LocalDateTime reservationDate;
    LocalDate rentalDate;

    public ReservationResponse(Reservation reservation) {
        this.id = Math.toIntExact(reservation.getId());
    }

}
