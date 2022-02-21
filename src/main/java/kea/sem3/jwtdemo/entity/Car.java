package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.CarRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    public Car() {}

    public Car(String brand, String model, double pricePrDay,double discount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = discount;
    }
    public Car(CarRequest body) {
        this.brand = body.getBrand();
        this.model = body.getModel();
        this.pricePrDay = body.getPricePrDay();
        this.bestDiscount = body.getBestDiscount();
    }

    String brand;

    //If problem related to transactionel, the use EAGER
    //mappedby: kigger på om reservation har noget der hedder reservedCar
    //fetch = fetchType.EAGER= man kan vælge to typer EAGER og LAZY
    //Hvis noget ikke bliver sendt så sæt den som EAGER.
    //LAZY er default men kan give nogle fejl nogle gange.
    //hvis fetch = FetchType ikke er sat så vil der pr default sætter sig som LAZY og derfor
    //blvier vi nødt til at sætte den på med EAGER til sidst for ikke at få fejl.
    //cascade = CascadeType.persist = hvis der er en entity navn og adresse og der mangler data i adr.
    //så vil den sørge for at tilføje.
    @OneToMany(mappedBy = "reservedCar", fetch = FetchType.EAGER)
    private Set<Reservation> reservations = new HashSet<>();

    public void addReservation(Reservation res){
        reservations.add(res);
    }

    @Column(length = 60)
    String model;

    double pricePrDay;

    //Best discount price (percent fo pricePrDay) an admin can offer
    double bestDiscount;

    //database har ccreated og edited som er annoteeret som gør at hver gang vi får lavet en car/member beder vi om at
    // i databasen skal created og edited i car tablet også laves. dvs. at i tablet cars vil created og edited blvier udfyldt med
    //en dato og tid i stedet for vi selv skal lave koden.
    @CreationTimestamp
    LocalDateTime created;

    //edited skal vi derfor heller ikke lave med kode da annotationen gør at hver gang der bliver lavet en ændring på en car
    //så vil der bliver ændret tal nede i databasen i car objekt i databasen i edited
    @UpdateTimestamp
    LocalDateTime edited;

}

