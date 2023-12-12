package dz.web.api.algeriacitiesdetails.helper;

import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 11/5/2023
 */
public class Utils {

    private Utils(){

    }

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
}
