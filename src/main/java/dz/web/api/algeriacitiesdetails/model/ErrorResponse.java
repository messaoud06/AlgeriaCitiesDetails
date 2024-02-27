package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @Author Messaoud GUERNOUTI on 2/27/2024
 */
@Setter
@Getter
public class ErrorResponse{

    private HttpStatus status;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;
    private String message;



    public ErrorResponse(HttpStatus status,String message){
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
