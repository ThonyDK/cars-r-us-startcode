package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.BaseUser;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
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

        Member m1 = new Member("xxx", "xxx@a.dk", "test12", "Kurt");
        m1.addRole(Role.USER);
        memberRepository.save(m1);




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
        Car c1 = new Car("Mercedes-Benz", "A45S", 5000,200);
        c1.setBrand("Mercedes-Benz");
        c1.setModel("A45S");
        c1.setPricePrDay(5000);

        Car c2 = new Car("Audi", "RS3", 4000,200);
        c2.setBrand("Audi");
        c2.setModel("RS3");
        c2.setPricePrDay(4000);

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
