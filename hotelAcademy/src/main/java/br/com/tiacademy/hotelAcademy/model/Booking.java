package br.com.tiacademy.hotelAcademy.model;

import java.time.LocalDate;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Booking {
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

    @ManyToOne
    @JoinColumn(name = "DEPENDENT_ID", referencedColumnName = "id")
    private Guest dependent;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @JsonIgnore
    private double bookingPrice;

    public String getFormattedPrice(){
        return "R$: " + bookingPrice + "0";
    }
}