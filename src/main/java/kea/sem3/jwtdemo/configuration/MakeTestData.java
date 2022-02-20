package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
//Denne Annotation @Profile gør, at netop denne test ikke skal køres
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public  void makePlainUsers(){
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);

        Member m1 = memberRepository.save(new Member("KW","kw@a.dk","test12","Kurt","Wonnegut","Lyngbyvje 34","Lyngby","2800"));
        Member m2 = memberRepository.save(new Member("HW","hw@a.dk","test12","Hanne","Wonnegut","Lyngbyvje 34","Lyngby","2800"));
        Member m3 = memberRepository.save(new Member("TD","td@a.dk","test12","Thony","Dyreborg","Lyngbyvje 34","Lyngby","2800"));

        //Car instanser som man kan reservere lige nedenfor i create a reservation
        Car carVolvo1 = carRepository.save(new Car("Volvo", "C40", 560,10));
        Car carVolvo2 = carRepository.save(new Car("Volvo", "V70", 500,10));
        carRepository.save(new Car("Volvo", "V49", 400,10));
        carRepository.save(new Car("Suzuki", "Vitara", 500,14));
        carRepository.save(new Car("Suzuki", "Vitara", 500,14));
        carRepository.save(new Car("Suzuki", "S-Cross", 500,14));

        //Create a Reservation
        //Create a reservation ved at tilføje flere fra instanserne ovenfor som fx carVolvo3
        //Hvis at den valgte bil fx carVolvo1 er booket kan den ikke double bookes samme dato. Se illustrationen nedenfor.
        Reservation res1 = new Reservation(LocalDate.of(2022,3,1),carVolvo1,m1);
        reservationRepository.save(res1);
        Reservation res2 = new Reservation(LocalDate.of(2022,4,1),carVolvo1,m2);
        reservationRepository.save(res2);
        Reservation res3 = new Reservation(LocalDate.of(2022,5,2),carVolvo2,m3);
        reservationRepository.save(res3);

        //Hvis at bilen er booket denne dato kan den ikke bookes
        Reservation res = reservationRepository.findReservationByReservedCar_IdAndRentalDate(carVolvo1.getId(),(LocalDate.of(2022,3,1)));
        if(res == null) {
            //Nedenfor er der prøvet på at lave en 4. reservation og den reservation er en reservation af carVolvo1 af member2 som prøver at booke bilen den samme dag som member1.
            //I konsollen vil der derfor stå at bilen er reserveret den dag.
            Reservation res4 = new Reservation(LocalDate.of(2022, 4, 1), carVolvo1, m2);
            reservationRepository.save(res4);
        } else{
            System.out.println("Car is reserved this day");
        }
        System.out.println(carVolvo1.getReservations().size());


        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("#################################### WARNING ! #########################################");
        System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
        System.out.println("########################################################################################");
        System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("Created TEST Users");

    }
    public void makeCars(){
        Car c1 = new Car("Mercedes-Benz", "A45S", 4500,200);
        c1.setBrand("Mercedes-Benz");
        c1.setModel("A45S");
        c1.setPricePrDay(4500);
        c1.setBestDiscount(200);

        Car c2 = new Car("Audi", "RS3", 4000,200);
        c2.setBrand("Audi");
        c2.setModel("RS3");
        c2.setPricePrDay(4000);
        c2.setBestDiscount(200);

        carRepository.save(c1);
        carRepository.save(c2);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();

        makePlainUsers();

        makeCars();

    }
}
