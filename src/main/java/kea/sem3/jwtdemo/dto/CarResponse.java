package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//Getter & Setter Annotation kan man lave så man ikke selv skal ned og laver dem på traditionel vis.
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    int id;
    //Change CarBrand into a String if you are not using an enum for this field
    String brand;
    String model;
    double pricePrDay;
    Double bestDiscount;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime updated;

    public CarResponse(Car car, boolean includeAll) {
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePrDay = car.getPricePrDay();
        this.id = car.getId();
        if (includeAll){
            this.created = car.getCreated();
            this.updated = car.getEdited();
            this.bestDiscount = car.getBestDiscount();
        }
        //We will do this together
    }

    public static List<CarResponse> getCarsFromEntities(List<Car> cars){
        return cars.stream().map(car-> new CarResponse(car, false)).collect(Collectors.toList());
        //We will do this together
    }
}

