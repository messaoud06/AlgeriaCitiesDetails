package dz.web.api.algeriacitiesdetails.repository;

import dz.web.api.algeriacitiesdetails.entity.Daira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 10/25/2023
 */
@Repository
public interface DairaRepository extends JpaRepository<Daira, Long> {


    Optional<Daira> findDairaByIdAndWilaya_WilayaCode(Long dairaId, String wilayaId);

    List<Daira> findAllByWilaya_WilayaCode(String WilayaCode);

}
