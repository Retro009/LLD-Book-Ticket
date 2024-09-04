package com.example.bmsbookticket.repositories;


import com.example.bmsbookticket.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

}
