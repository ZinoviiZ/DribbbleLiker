package com.zubko.liker.repository;

import com.zubko.liker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
