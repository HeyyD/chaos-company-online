package fi.chaoscompany.server.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MapController {
    private final int[][] map = new int[][]{
            {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 6},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 0}
    };

    Logger logger = LoggerFactory.getLogger(MapController.class);

    @MessageMapping("/map")
    @SendTo("/map")
    public MapMessage send(MapMessage msg) {
        logger.info("Received tile map request");
        return new MapMessage(map);
    }
}
