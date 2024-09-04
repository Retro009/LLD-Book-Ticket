package com.example.bmsbookticket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Screen extends BaseModel{
    private String name;
    @OneToMany
    private List<Seat> seats;
    @Enumerated(EnumType.ORDINAL)
    private ScreenStatus status;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> features;
    @ManyToOne
    private Theatre theatre;
}
