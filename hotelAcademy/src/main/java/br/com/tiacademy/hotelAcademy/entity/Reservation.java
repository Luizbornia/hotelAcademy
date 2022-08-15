package br.com.tiacademy.hotelAcademy.entity;

import br.com.tiacademy.hotelAcademy.repository.CalculateReservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initialDate;
    private String finalDate;

    // private Room room;
    // private MainGuestId mainGuestid;
}