package br.com.tiacademy.hotelAcademy.dto;

import br.com.tiacademy.hotelAcademy.model.RoomStatus;
import br.com.tiacademy.hotelAcademy.model.RoomType;
import br.com.tiacademy.hotelAcademy.model.Sleep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto {

    private Long roomNumber;
    private Sleep sleep;
    private RoomType roomType;
}
