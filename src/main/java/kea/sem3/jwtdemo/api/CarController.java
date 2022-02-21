package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RESTController: Gør det samme som en normal controller
//RequestMapping: Gør at api/cars bliver sat på automatisk på alle GetMapping stederne i klassen.
@RestController
@RequestMapping("api/cars")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars();
    }
    //Den id der står ved @PatchVarible henviser til den {id} der står i endpointet og de skal matche.
    @GetMapping("/{id}")
    public CarResponse getCars(@PathVariable int id) throws Exception{
        return carService.getCar(id, false);
    }
    //@RequestBody: er en reference fra http protocollen. det henter bodiet fra http requestet over i CarRequest. Den
    //giver os det der kommer ind.
    //CarResponse er Json file der bliver sendt tilbage.
    @PostMapping
    public CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    @PutMapping("/{id}")
    public CarResponse editCar(@RequestBody CarRequest body, @PathVariable int id){
        return carService.editCar(body,id);
    }
    @PatchMapping("/{id}/{newprice}")
    public void editPrice(@PathVariable int id, @PathVariable double newprice) throws Exception{
        carService.updatePrice(id, newprice);
    }
    //DeleteMapping
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
    }
}

