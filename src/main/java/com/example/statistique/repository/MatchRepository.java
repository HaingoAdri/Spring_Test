package com.example.statistique.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.statistique.models.Match;


public interface MatchRepository extends JpaRepository<Match, Integer> {
    @Query(value = "SELECT * from match where id = :id", nativeQuery = true)
    Optional<Match> findById(@Param("id") int id);
    @Query(value = "select count(*) nombre from match where id_equipe1 = :idEquipe or id_equipe2 = :idEquipe", nativeQuery = true)
    int matchJouer(@Param("idEquipe") int idEquipe);
}