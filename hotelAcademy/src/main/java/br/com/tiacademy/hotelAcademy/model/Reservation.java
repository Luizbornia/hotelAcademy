package br.com.tiacademy.hotelAcademy.model;

import br.com.tiacademy.hotelAcademy.CalculateReservation;
import br.com.tiacademy.hotelAcademy.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    @JoinColumn(name = "ROOM_NUMBER", referencedColumnName = "roomNumber")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "MAIN_GUEST_ID", referencedColumnName = "id")
    private Guest guest;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    //private CalculateReservation reservationValue;

    //CalculateReservation calculateReservation = new CalculateReservation();
}