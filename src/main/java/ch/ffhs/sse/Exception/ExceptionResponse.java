package ch.ffhs.sse.Exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private String message;
    private LocalDateTime dateTime;
    public void setMessage(String message) {
        this.message = message;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}