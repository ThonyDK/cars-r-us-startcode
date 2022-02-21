package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.sem3.jwtdemo.dto.MemberRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


//Lombok genere getters og setters og constructors
//ALlargsConstructor: laver en enkelt constructor så vi ikke skal lave dem selv med linjer kode
//NoArgsconstructor: Den vil lave en tom constructor for os.
//Entity klassen/Klasserne mapper direkte ned til databasen.
@Entity
//Getters og Setters bliver indsat automatisk ved nedenstående annotationer.
@Getter
@Setter
//DiscriminatorValue: i databasen står der MEMBER pga. vi har sat denne annotation på
//Så hvis man ændre den til medlem vil der stå medlem i databasen.
//Normalt for at lave en entity skal der være annotation @Entity og annotation @ID
//Men i dette tilfælde er der nedarvet id fra baseUser klassen.
@DiscriminatorValue("MEMBER")
public class Member extends BaseUser {

    @Column(length = 40)
    String firstName;

    @Column(length = 60)
    String lastName;

    @Column(length = 60)
    String street;

    @Column(length = 30)
    String city;

    @Column(length = 4)
    String zip;

    @Column(name = "ranking")
    int ranking;

    @Column(name = "approved")
    boolean isApproved;

    //database har ccreated og edited som er annoteeret som gør at hver gang vi får lavet en car/member beder vi om at
    // i databasen skal created og edited i car tablet skal der laves enm tid og dato. dvs. at i tablet cars vil created og edited bliverer udfyldt med
    // en dato og tid i stedet for vi selv skal lave koden.
    @Column(name = "created")
    @CreationTimestamp
    LocalDateTime created;

    //edited skal vi derfor heller ikke lave med kode da annotationen gør at hver gang der bliver lavet en ændring på en car
    //så vil der bliver ændret tal nede i databasen i car/member objekt i databasen i edited.
    @Column(name = "edited")
    @UpdateTimestamp
    LocalDateTime edited;


    @JsonIgnore
    //mappedby: kigger på om reservation har noget der hedder reservedTo
    @OneToMany(mappedBy = "reservedTo")
    private Set<Reservation> reservations = new HashSet<>();

    public void addReservation(Reservation res){
        reservations.add(res);
    }

    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, String zip) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.ranking = 5; //Initial ranking
        this.isApproved = false;
    }
    public Member(MemberRequest body) {
        //Just call the constructor above
        this(body.getUsername(),body.getEmail(),body.getPassword(),body.getFirstName(),body.getLastName(),body.getStreet(),body.getCity(),body.getZip());
    }

    public Member() {
    }
}
