package uz.pdp.railwayticketproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.railwayticketproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAndEnabledTrue(String email);

}