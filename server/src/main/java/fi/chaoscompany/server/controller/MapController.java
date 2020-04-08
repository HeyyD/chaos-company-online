package fi.chaoscompany.server.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MapController {
    Logger logger = LoggerFactory.getLogger(MapController.class);

    @MessageMapping("/map")
    @SendTo("/map")
    public Message send(Message message) {
        logger.info("Received: " + message.getMsg());
        return new Message("Greetings");
    }
}
