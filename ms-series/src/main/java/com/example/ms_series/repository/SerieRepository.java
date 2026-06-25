package com.example.ms_series.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_series.model.Serie;;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer> {
}

