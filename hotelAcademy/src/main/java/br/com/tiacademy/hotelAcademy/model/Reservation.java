package br.com.tiacademy.hotelAcademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "roomNumber", referencedColumnName = "roomNumber")
    private Room room;

    // private MainGuestId mainGuestid;

    //CalculateReservation calculateReservation = new CalculateReservation();
}