package fi.chaoscompany.server.controller;

import fi.chaoscompany.server.models.GameObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RestController(value = "api/game")
public class GameObjectController {

    private Map<Integer, GameObject> objects = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public Map<Integer, GameObject> getGameObjects() {
        return objects;
    }
}
