package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
    User findBySnsIdAndJoinPath(String snsId, String registration);

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserIdAndUserName(String userId, String userName);
}
