package dz.web.api.algeriacitiesdetails.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * @Author Messaoud GUERNOUTI on 11/22/2023
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Algeria Cities REST API Documentation",
                description = "Algeria Cities Details REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Messaoud GUERNOUTI",
                        email = "messaoud06@gmail.com",
                        url = "https://resume.messaoud-guernouti.info"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.messaoud-guernouti.info"
                )
        )
)
public class SwaggerDefConfig {
}
