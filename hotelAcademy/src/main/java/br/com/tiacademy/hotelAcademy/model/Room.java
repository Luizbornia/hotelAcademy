package br.com.tiacademy.hotelAcademy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Room {

    @Id
    private Long roomNumber;
    
    @Enumerated(EnumType.STRING)
    private Sleep sleep;
    
    @Column(name = "ROOM_TYPE")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "ROOM_STATUS")
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

}