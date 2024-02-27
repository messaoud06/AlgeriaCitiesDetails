package dz.web.api.algeriacitiesdetails.helper;

import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Commune;
import dz.web.api.algeriacitiesdetails.entity.Daira;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.exception.GenericException;
import dz.web.api.algeriacitiesdetails.model.WilayaDtoRecord;
import dz.web.api.algeriacitiesdetails.service.CommuneService;
import dz.web.api.algeriacitiesdetails.service.DairaService;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 11/5/2023
 */

public class Utils {


     private static final List<String> POSSIBLE_IP_HEADERS = List.of(
            "X-Forwarded-For",
            "HTTP_FORWARDED",
            "HTTP_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_CLIENT_IP",
            "HTTP_VIA",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "REMOTE_ADDR"
    );

    private static final String LOCALHOST_IPV4 = "127.0.0.1";
    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    public static String getIpAddressFromHeader(HttpServletRequest request) {
        String headerValue = null;
        for (String ipHeader : POSSIBLE_IP_HEADERS) {
            headerValue = Collections.list(request.getHeaders(ipHeader)).stream()
                    .filter(StringUtils::hasLength)
                    .findFirst()
                    .orElse(null);

            if (headerValue != null) {
                return headerValue;
            }
        }

        if (request.getRemoteAddr().equalsIgnoreCase(LOCALHOST_IPV4) ||
            request.getRemoteAddr().equalsIgnoreCase(LOCALHOST_IPV6))
        {
            return "localhost";
        }

        return null;
    }


    public static void updateJsonFilter(WilayaDetail wilayaDetail){

        JacksonProviderConfig.fieldNames.clear();


        switch (wilayaDetail){
            case WILAYA_ONLY -> JacksonProviderConfig.fieldNames.add(JacksonProviderConfig.JSON_FILTER_DAIRA);
            case DAIRA_ONLY -> JacksonProviderConfig.fieldNames.add(JacksonProviderConfig.JSON_FILTER_COMMUNE);
            case COMMUNE_ONLY -> JacksonProviderConfig.fieldNames.add(JacksonProviderConfig.JSON_FILTER_POST);
            default  ->   JacksonProviderConfig.fieldNames.clear();
        }

    }


    public static boolean isValidDate(String format,String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);


        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    public static String getNameById(String id, WilayaService wilayaService, DairaService dairaService, CommuneService communeService){

        Optional<WilayaDtoRecord> wilayaById = wilayaService.getWilayaById(id, WilayaDetail.WILAYA_ONLY);


        if (wilayaById.isPresent()){
            return wilayaById.get().WilayaNameFr();
        }

        Optional<Daira> dairaById = dairaService.getDairaById(Long.valueOf(id));

        if (dairaById.isPresent()){
            return dairaById.get().getDairaNameFr();
        }

        Optional<Commune> communeById = communeService.findCommuneById(Long.valueOf(id));

        if (communeById.isPresent()){
            return communeById.get().getCommuneNameFr();
        }

        throw new GenericException("Id Wilaya/Daira/commune Not Found");
    }


    public static String validatePrayerDate(String date){

        if(date==null){
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = today.format(formatter);
        }

        if(!Utils.isValidDate("dd-MM-yyyy",date)){
            throw new GenericException("Format Date incorrect");
        }

        return date;
    }
}
