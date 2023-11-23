package dz.web.api.algeriacitiesdetails.model;

import dz.web.api.algeriacitiesdetails.entity.Commune;
import dz.web.api.algeriacitiesdetails.entity.PostDetail;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 11/22/2023
 */
@Schema(name = "Post",
        description = "Post information"
)
public record PostDetailDto(String postalCode,
                            String postNameFr,
                            String postNameAr,
                            String postAddressFr,
                            String postAddressAr) {



    public static PostDetailDto build(PostDetail postDetail){
        return new PostDetailDto(postDetail.getPostalCode(), postDetail.getPostNameFr(), postDetail.getPostNameAr(), postDetail.getPostAddressFr(), postDetail.getPostAddressAr());
    }
}
