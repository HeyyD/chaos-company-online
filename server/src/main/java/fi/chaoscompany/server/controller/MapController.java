package fi.chaoscompany.server.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "/api/map")
public class MapController {
    Logger logger = LoggerFactory.getLogger(MapController.class);

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

    @RequestMapping(method = RequestMethod.GET)
    public MapMessage getMap() {
        return new MapMessage(map);
    }
}
