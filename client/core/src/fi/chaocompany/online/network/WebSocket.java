package fi.chaocompany.online.network;

import com.badlogic.gdx.Gdx;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WebSocket {

    private final static String LOG_TAG = WebSocket.class.getSimpleName();

    private static WebSocket instance;

    private StompSession session = null;

    private List<OnConnectListener> listeners = new ArrayList<>();

    private WebSocket() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new StompSessionHandler() {
            @Override
            public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
                Gdx.app.log(LOG_TAG, "Connected to server");

                session = stompSession;
                listeners.forEach(OnConnectListener::onConnect);
            }

            @Override
            public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void handleTransportError(StompSession stompSession, Throwable throwable) {
                Gdx.app.log(LOG_TAG, "TRANSPORT ERROR");
            }

            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) { }
        };

        stompClient.connect("ws://localhost:8080/chaos-company", sessionHandler);
    }

    public OnConnectListener registerOnConnectListener(OnConnectListener listener) {
        listeners.add(listener);
        return listener;
    }

    public void unRegisterOnConnectListener(OnConnectListener listener) {
        listeners.remove(listener);
    }

    public void send(String destination, Object message) {
        this.session.send(destination, message);
    }

    public StompSession.Subscription subscribe(String destination, StompFrameHandler handler) {
        return this.session.subscribe(destination, handler);
    }

    public static WebSocket getInstance() {
        if (instance == null) {
            instance = new WebSocket();
        }
        return instance;
    }
}
