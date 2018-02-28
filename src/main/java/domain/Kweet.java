package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
public class Kweet {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull @Column(columnDefinition = "int default 140")
    private int maxAmountOfCharacters;

    @NotNull
    private String message;

    @NotNull
    private Date timeOfPosting;

    /**
     * Empty constructor for the ORM
     */
    public Kweet() {

    }



}
