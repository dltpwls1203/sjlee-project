package com.sjlee.web.toy.ground.repository;

import com.sjlee.web.toy.ground.domain.Ground;
import com.sjlee.web.toy.ground.dto.GroundSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroundRepository extends JpaRepository<Ground, Long> {
    @Query("""
        select new com.sjlee.web.toy.ground.dto.GroundSelect(
            g.id,
            g.name
        )
        from Ground g
        order by g.name
    """)
    List<GroundSelect> findSelectItems();
}
