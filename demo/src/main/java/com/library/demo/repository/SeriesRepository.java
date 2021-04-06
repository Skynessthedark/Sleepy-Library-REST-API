package com.library.demo.repository;

import java.util.List;

import com.library.demo.domain.SeriesDO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<SeriesDO, Long> {
    
    List<SeriesDO> findAllByName(String seriesName);
}
