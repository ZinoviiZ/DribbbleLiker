package com.zubko.liker.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Data
@Entity
public class DribbbleShot {

    @Id
    private String id;
    private String title;
    private String description;
    private String views_count;
    private String likes_count;
    private String comments_count;

    @ElementCollection(targetClass=String.class)
    private List<String> tags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
