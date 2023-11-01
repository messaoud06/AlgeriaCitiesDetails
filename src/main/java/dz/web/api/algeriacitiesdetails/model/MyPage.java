package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 10/29/2023
 */
public class MyPage<Wilaya> extends PageImpl<Wilaya> implements Serializable {

    static final long serialVersionUID=1L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MyPage(@JsonProperty("content") List<Wilaya> content,
                           @JsonProperty("pageable") JsonNode pageable,
                           @JsonProperty("totalPages") int totalePages,
                           @JsonProperty("last") boolean last,
                           @JsonProperty("totalElements") Long totalElements,
                           @JsonProperty("size") int size,
                           @JsonProperty("number") int number,
                           @JsonProperty("numberOfElements") int numberOfElements,
                           @JsonProperty("sort") JsonNode sort,
                           @JsonProperty("first") boolean first

    ){
        super(content, PageRequest.of(number,size),totalElements);
    }


    public MyPage(List<Wilaya> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public MyPage(List<Wilaya> content) {
        super(content);
    }
}
