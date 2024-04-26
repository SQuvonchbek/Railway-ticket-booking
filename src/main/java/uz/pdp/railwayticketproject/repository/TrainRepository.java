package uz.pdp.railwayticketproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.railwayticketproject.entity.Train;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    boolean existsByNameAndStatusTrue(String name);

    List<Train> findAllByName(final String name);
}
