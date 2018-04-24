package event;

import domain.Kweet;

/**
 * Created by Martijn van der Pol on 15-03-18
 **/
public class KweetEvent {

    private Kweet kweet;

    public KweetEvent(Kweet kweet) {
        this.kweet = kweet;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }
}
