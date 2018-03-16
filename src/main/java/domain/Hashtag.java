package domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
@NamedQueries(
        @NamedQuery(name = "Hashtag.findBySubject", query = "SELECT hashtag FROM Hashtag hashtag WHERE hashtag.subject LIKE :subject")
)
public class Hashtag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String subject;

    @OneToMany
    private List<Kweet> kweets;

    /**
     * Empty constructor for the ORM
     */
    public Hashtag() {

    }

    /**
     * Constructor for the Hashtag class
     *
     * @param subject     is the subject of the hashtag, for example '#DonaldTrump'
     */
    public Hashtag(String subject) {
        this.kweets = new ArrayList<Kweet>();
        this.subject = subject;
    }

    //<editor-fold desc="Getters and Setters">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    //</editor-fold>

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("subject", this.subject)
                .add("kweets", this.kweets.size())
                .build();
    }

    public JsonObject toHref() {
        return Json.createObjectBuilder()
                .add("href", this.id)
                .build();
    }
}
