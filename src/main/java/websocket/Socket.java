package websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Martijn van der Pol on 14-05-18
 **/
@ServerEndpoint(value = "/socket/{username}")
public class Socket {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println(session.getId() + " has opened a connection");
        session.getBasicRemote().sendText("Welcome " + session.getId() + "!");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Message from " + session.getId() + ": " + message);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " + session.getId() + " has ended");
    }

}
