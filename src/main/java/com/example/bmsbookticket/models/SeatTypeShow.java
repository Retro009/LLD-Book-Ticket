package com.example.bmsbookticket.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeatTypeShow extends BaseModel{
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    private double price;
}
