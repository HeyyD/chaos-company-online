package fi.chaoscompany.server.controller;

import fi.chaoscompany.server.models.GameObject;
import fi.chaoscompany.server.models.UpdateMessage;
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
    private int currentId = 0;

    private Map<Integer, GameObject> objects = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public Map<Integer, GameObject> getGameObjects() {
        return this.objects;
    }

    @MessageMapping("/object/add")
    @SendTo("/object")
    public GameObject addGameObject(GameObject gameObject) {
        gameObject.setId(currentId);
        currentId++;
        this.objects.put(gameObject.getId(), gameObject);
        return new GameObject(this.objects.get(gameObject.getId()));
    }

    @MessageMapping("/update")
    @SendTo("/update")
    public UpdateMessage updateGameObject(UpdateMessage update) {
        return new UpdateMessage("Update received");
    }

    @MessageMapping("/delete")
    @SendTo("/delete")
    public int deleteGameObject(int key) {
        this.objects.remove(key);
        return key;
    }
}
