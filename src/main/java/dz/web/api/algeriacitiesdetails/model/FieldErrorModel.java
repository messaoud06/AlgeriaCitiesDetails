package dz.web.api.algeriacitiesdetails.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FieldErrorModel {
    private String field;
    private List<String> messages;
}
