package io.bootify.l10_minor_project.repos;

import io.bootify.l10_minor_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    User findByEmail(String email);

}
