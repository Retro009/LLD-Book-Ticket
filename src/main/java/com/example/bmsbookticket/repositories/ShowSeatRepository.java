package com.example.bmsbookticket.repositories;


import com.example.bmsbookticket.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    List<ShowSeat> findByIdIn(List<Integer> showSeatIds);


}
