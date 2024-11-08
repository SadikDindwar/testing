package com.example.repositoy;

import com.example.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {
}