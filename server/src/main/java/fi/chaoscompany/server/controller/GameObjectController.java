package fi.chaoscompany.server.controller;

import fi.chaoscompany.server.models.GameObject;
import fi.chaoscompany.server.models.UpdateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(GameObjectController.class);

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
        int id = update.getId();
        GameObject object = this.objects.get(id);

        if (object.getSessionId().equals(update.getSessionId())) {
            object.setX(update.getX());
            object.setY(update.getY());
        }
        return new UpdateMessage(id, object.getX(), object.getY(), update.getSessionId());
    }

    @MessageMapping("/delete")
    @SendTo("/delete")
    public int deleteGameObject(int key) {
        this.objects.remove(key);
        return key;
    }
}
