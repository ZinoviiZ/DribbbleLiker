package com.zubko.liker.worker;

import com.zubko.liker.entity.User;
import com.zubko.liker.service.DribbbleService;
import com.zubko.liker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Component
public class LikeWorker extends Thread {

    @Autowired private DribbbleService dribbbleService;
    @Autowired private UserService userService;

    public void run() {
        while (true) {
            List<User> users;
            break;
        }
    }
}
