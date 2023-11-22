package dz.web.api.algeriacitiesdetails.model;

import dz.web.api.algeriacitiesdetails.entity.Commune;
import dz.web.api.algeriacitiesdetails.entity.Daira;

import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 11/22/2023
 */
public record CommuneDto(String communeNameFr, String communeNameAr, List<PostDetailDto> postDetailDtoList) {

    public static CommuneDto build(Commune commune){

        List<PostDetailDto> postDetailDto = commune.getPostDetails()
                .stream()
                .map(PostDetailDto::build)
                .toList();
        return new CommuneDto(commune.getCommuneNameFr(), commune.getCommuneNameAr(), postDetailDto);
    }
}
