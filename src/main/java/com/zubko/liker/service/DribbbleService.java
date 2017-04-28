package com.zubko.liker.service;

import com.zubko.liker.connector.DribbbleConnector;
import com.zubko.liker.entity.DribbbleShot;
import com.zubko.liker.model.common.DribbbleKeys;
import com.zubko.liker.model.enums.StatusResponse;
import com.zubko.liker.model.response.DribbbleAccessTokenResponse;
import com.zubko.liker.model.response.DribbbleLikeShotResponse;
import com.zubko.liker.model.response.DribbbleUserResponse;
import com.zubko.liker.model.response.DribbbleUserShotsResponse;
import com.zubko.liker.repository.ShotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Service
public class DribbbleService {

    @Autowired private DribbbleConnector dribbbleConnector;
    @Autowired private ShotRepository shotRepository;

    public DribbbleAccessTokenResponse getAccessToken(String code) {
        return dribbbleConnector.invokeGetAccessToken(code);
    }

    public DribbbleUserResponse getCurrentUser(String accessToken) {
        return dribbbleConnector.invokeGetCurrentUser(accessToken);
    }

    public DribbbleLikeShotResponse likePost(String accessToken, String shotId) {
        return dribbbleConnector.invokeLikeShot(accessToken, shotId);
    }

    public DribbbleUserShotsResponse getUserShots(String accessToken, String userId) {
        return dribbbleConnector.invokeGetUserShots(accessToken, userId);
    }

    public List<DribbbleShot> getNewShots(String accessToken) {
        List shots = dribbbleConnector.invokeGetUserShots(accessToken, DribbbleKeys.ZUBKO_YAROSLAV_ID).getShots();
        return filterShots(shots);
    }

    private List<DribbbleShot> filterShots(List<DribbbleShot> shots) {
        List<DribbbleShot> alreadyLiked = shotRepository.findAll();
        Iterator<DribbbleShot> shotIterator = shots.listIterator();
        while (shotIterator.hasNext()) {
            if (alreadyLiked.contains(shotIterator.next()))
                shotIterator.remove();
        }
        return shots;
    }
}
