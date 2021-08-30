package com.appleyk.repository;

import com.appleyk.model.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liangguannan on 2020/5/14.
 */
public interface DiseaseRepository extends Neo4jRepository<Movie,Long> {

    /**
     * 14 对应疾病的治疗方案
     * @param title
     * @return
     */
    @Query("match(n:coronavirus) where n.title={title} return n")
    List<String> getTreatment(@Param("title") String title);
}
