package dz.web.api.algeriacitiesdetails.repository;

import dz.web.api.algeriacitiesdetails.entity.Wilaya;
import dz.web.api.algeriacitiesdetails.model.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 10/25/2023
 */
@Repository
public interface WilayaRepository extends JpaRepository<Wilaya, Long> {

    @Query("SELECT w FROM Wilaya w")
    Page<Wilaya> findAllWithPagination(Pageable pageable);

    Optional<Wilaya> findWilayasByWilayaCode(String wilayaCode);

}
