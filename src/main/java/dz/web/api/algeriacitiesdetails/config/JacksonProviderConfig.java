package dz.web.api.algeriacitiesdetails.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Messaoud GUERNOUTI on 10/29/2023
 */
@Configuration
public class JacksonProviderConfig {

    public static final String JSON_FILTER_DAIRA ="Dairas";
    public static final String JSON_FILTER_COMMUNE ="Communes";
    public static final String JSON_FILTER_POST ="Postes";
    public static final String JSON_FILTER_PRAYER_TIMES ="PrayerTimes";

    public static  Set<String> fieldNames = new HashSet<>();

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);
        FilterProvider filters = simpleFilterProvider.addFilter("detailsFilter", SimpleBeanPropertyFilter.serializeAllExcept(fieldNames));
        objectMapper.setFilterProvider(filters);

        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }


}
