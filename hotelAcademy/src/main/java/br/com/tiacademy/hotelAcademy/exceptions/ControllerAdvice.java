package br.com.tiacademy.hotelAcademy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> roomNotFound(RoomNotFoundException exception, HttpServletRequest request){
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Not Found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RoomAlreadyOccupiedException.class)
    public ResponseEntity<StandardErrorResponse> RoomAlreadyOccupied(RoomAlreadyOccupiedException exception, HttpServletRequest request) {
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setError("Not Acceptable");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(MainGuestNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> MainGuestNotFound(MainGuestNotFoundException exception, HttpServletRequest request) {
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Not Found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MainGuestAlreadyInBookingException.class)
    public ResponseEntity<StandardErrorResponse> MainGuestAlreadyInBooking(MainGuestAlreadyInBookingException exception, HttpServletRequest request) {
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Not Found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DependentNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> DependentNotFound(DependentNotFoundException exception, HttpServletRequest request) {
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Not Found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DependentAlreadyInBookingException.class)
    public ResponseEntity<StandardErrorResponse> RoomAlreadyOccupied(DependentAlreadyInBookingException exception, HttpServletRequest request) {
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setError("Not Acceptable");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(MainGuestAndDependentAreTheSameException.class)
    public ResponseEntity<StandardErrorResponse> MainGuestAndDependentAreTheSame(MainGuestAndDependentAreTheSameException exception, HttpServletRequest request) {
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setError("Not Acceptable");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> DependentNotFound(BookingNotFoundException exception, HttpServletRequest request) {
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Not Found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}