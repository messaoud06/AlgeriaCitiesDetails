package dz.web.api.algeriacitiesdetails.repository;

import dz.web.api.algeriacitiesdetails.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 10/30/2023
 */
@Repository
public interface CommuneRepository extends JpaRepository<Commune,Long> {

    Optional<Commune> findCommuneByIdAndDaira_IdAndDaira_Wilaya_WilayaCode(Long communeId,Long dairaId,String wilayaId);

    List<Commune> findAllByDaira_IdAndDaira_Wilaya_WilayaCode(Long dairaId, String wilayaCode);

}
