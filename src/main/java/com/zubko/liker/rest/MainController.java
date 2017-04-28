package com.zubko.liker.rest;

import com.zubko.liker.model.response.DribbbleUserShotsResponse;
import com.zubko.liker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@RestController
public class MainController {

    @Autowired private UserService userService;

    @RequestMapping(value = "/like/{userId}")
    private ResponseEntity likeShot(@PathVariable Long userId) {
        return userService.likePost(userId, "3456814");
    }

    @RequestMapping(value = "/shots/{userId}")
    private DribbbleUserShotsResponse getUserShots(@PathVariable Long userId) {
        return userService.getUserShots(userId);
    }
}
