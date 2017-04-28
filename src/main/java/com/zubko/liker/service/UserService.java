package com.zubko.liker.service;

import com.zubko.liker.entity.User;
import com.zubko.liker.model.common.DribbbleKeys;
import com.zubko.liker.model.enums.StatusResponse;
import com.zubko.liker.model.response.DribbbleAccessTokenResponse;
import com.zubko.liker.model.response.DribbbleLikeShotResponse;
import com.zubko.liker.model.response.DribbbleUserResponse;
import com.zubko.liker.model.response.DribbbleUserShotsResponse;
import com.zubko.liker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.zubko.liker.model.common.DribbbleKeys.ZUBKO_YAROSLAV_ID;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private DribbbleService dribbbleService;

    public StatusResponse addUser(String code) {

        DribbbleAccessTokenResponse accessTokenResponse = dribbbleService.getAccessToken(code);
        DribbbleUserResponse dribbbleUser = dribbbleService.getCurrentUser(accessTokenResponse.getAccess_token());
        User user = new User(dribbbleUser.getUsername(), dribbbleUser.getId(), accessTokenResponse.getAccess_token());
        userRepository.save(user);
        return StatusResponse.SUCCESS;
    }

    public ResponseEntity likePost(String userId, String shotId) {

        User user = userRepository.findOne(userId);
        DribbbleLikeShotResponse likeShotResponse = dribbbleService.likePost(user.getDribbbleAccessToken(), shotId);
        return new ResponseEntity(HttpStatus.OK);
    }

    public DribbbleUserShotsResponse getUserShots(String userId) {
        User user = userRepository.findOne(userId);
        DribbbleUserShotsResponse userShotsResponse = dribbbleService.getUserShots(user.getDribbbleAccessToken(), ZUBKO_YAROSLAV_ID);
        return userShotsResponse;
    }
}
