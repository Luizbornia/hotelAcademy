package br.com.tiacademy.hotelAcademy.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate initialDate;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate finalDate;

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