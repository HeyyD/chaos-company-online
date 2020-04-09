package fi.chaoscompany.server.controller;

import fi.chaoscompany.server.models.GameObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/game")
public class GameObjectController {
    private Map<Integer, GameObject> objects = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public Map<Integer, GameObject> getGameObjects() {
        return this.objects;
    }

    @MessageMapping("/object")
    @SendTo("/object")
    public GameObject addGameObject(GameObject gameObject) throws Exception {
        // this.objects.put(objects.size(), gameObject);
        return gameObject;
    }
}
