package com.zubko.liker.model.response;

import com.zubko.liker.entity.DribbbleShot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DribbbleUserShotsResponse {

    private List<DribbbleShot> shots;
}
