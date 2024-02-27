package dz.web.api.algeriacitiesdetails.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @Author Messaoud GUERNOUTI on 2/27/2024
 */
@Setter
@Getter
public class GenericException extends RuntimeException {

    private String message;


    public GenericException(String message){
        this.message = message;
    }



}
