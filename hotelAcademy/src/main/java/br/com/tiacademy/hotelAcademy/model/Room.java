package br.com.tiacademy.hotelAcademy.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Room {

    @Id
    private Long roomNumber;
    
    @Enumerated(EnumType.STRING)
    private Sleep sleep;
    
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

}