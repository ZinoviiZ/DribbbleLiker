package com.zubko.liker.entity;

import com.zubko.liker.model.response.DribbbleUserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;
    private String userName;
    private String dribbbleAccessToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<DribbbleShot> likedShots;

    public User() {
        this.likedShots = new ArrayList<>();
    }

    public User(String userName, String dribbbleId, String dribbbleAccessToken) {
        this.id = dribbbleId;
        this.userName = userName;
        this.dribbbleAccessToken = dribbbleAccessToken;
        this.likedShots = new ArrayList<>();
    }
}
