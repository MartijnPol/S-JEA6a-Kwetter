package websocket;

import domain.UserProfile;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martijn van der Pol on 22-05-18
 **/
@ApplicationScoped
public class Handler {

    private final Set<Session> sessions = new HashSet<>();
    private final Set<UserProfile> userProfiles = new HashSet<>();

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public void removeSession(Session session) {
        this.sessions.remove(session);
    }

    public void addUserProfile(UserProfile userProfile) {
        this.userProfiles.add(userProfile);
    }

    public void removeUserProfile(UserProfile userProfile) {
        this.userProfiles.remove(userProfile);
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

}
