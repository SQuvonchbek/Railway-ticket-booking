package uz.pdp.railwayticketproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.railwayticketproject.entity.VerificationCode;

import java.time.LocalDateTime;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

  void deleteByCreatedDateBefore(LocalDateTime createdDate);


}