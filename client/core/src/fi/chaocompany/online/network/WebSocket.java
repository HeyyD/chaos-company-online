package fi.chaocompany.online.network;

import com.badlogic.gdx.Gdx;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

public class WebSocket {

    private final static String LOG_TAG = WebSocket.class.getSimpleName();

    private static WebSocket instance;

    private WebSocket() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new StompSessionHandler() {
            @Override
            public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
                Gdx.app.log(LOG_TAG, "CONNECTED");
                // stompSession.subscribe("/topic/messages", this);
                // stompSession.send("/app/chat", "Hello world!");
            }

            @Override
            public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void handleTransportError(StompSession stompSession, Throwable throwable) {

            }

            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                // String msg = (String) o;
                // Gdx.app.log(LOG_TAG, "Received : " + msg);
            }
        };

        stompClient.connect("ws://localhost:8080/chaos-company", sessionHandler);
    }

    public static WebSocket getInstance() {
        if (instance == null) {
            instance = new WebSocket();
        }
        return instance;
    }
}
