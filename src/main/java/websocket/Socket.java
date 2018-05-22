package websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Martijn van der Pol on 14-05-18
 **/
@ApplicationScoped
@ServerEndpoint(value = "/socket/{username}")
public class Socket {

    @Inject
    private Handler handler;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException {
        this.handler.addSession(session);
        System.out.println(session.getId() + " has opened a connection");
        session.getBasicRemote().sendText("Welcome " + session.getId() + "!");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        handler.getSessions().forEach(handlerSession -> handlerSession.getAsyncRemote().sendText(message));
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " + session.getId() + " has ended");
    }

}
