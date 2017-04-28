package com.zubko.liker.rest;

import com.zubko.liker.model.enums.StatusResponse;
import com.zubko.liker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@RestController
public class DribbbleController {

    @Autowired private UserService userService;

    @RequestMapping(value = "/dribbble/callback")
    private StatusResponse callback(@RequestParam String code) {
        return userService.addUser(code);
    }
}
