package dz.web.api.algeriacitiesdetails;

import dz.web.api.algeriacitiesdetails.entity.*;
import dz.web.api.algeriacitiesdetails.repository.WilayaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author Messaoud GUERNOUTI on 10/25/2023
 */

//@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
/*
    private final WilayaRepository wilayaRepository;

    private final TmpCitiesPostalRepository tmpCitiesPostalRepository;

    @Override
    public void run(String... args) throws Exception {

        List<TmpCitiesPostal> all = tmpCitiesPostalRepository.findAll();


       List<Wilaya> wilayas = all.stream()
                .filter(distinctByKeys(TmpCitiesPostal::getWilaya_name_ascii))
                .map(tmpCities -> new Wilaya
                        (null, tmpCities.getWilaya_code(), tmpCities.getWilaya_name_ascii(), tmpCities.getWilaya_name(), null))
                .sorted(Comparator.comparing(Wilaya::getWilayaCode))
                .collect(Collectors.toList());


        wilayas.stream()
                .sorted(Comparator.comparing(Wilaya::getWilayaCode))
                .forEach(wilaya -> {
                    List<Daira> dairas1 = all.stream().filter(distinctByKeys(TmpCitiesPostal::getDaira_name,TmpCitiesPostal::getWilaya_code))
                            .filter(tmpCities -> wilaya.getWilayaCode().equalsIgnoreCase(tmpCities.getWilaya_code()))
                            .map(tmpCities -> new Daira(null, tmpCities.getDaira_name_ascii(), tmpCities.getDaira_name(),wilaya,null))
                            .collect(Collectors.toList());
                    wilaya.setDairaSet(new HashSet<>(dairas1));
                    //wilayaRepository.save(wilaya);
                });

        wilayas.stream()
                .sorted(Comparator.comparing(Wilaya::getWilayaCode))
                .forEach(wilaya -> {
                    wilaya.getDairaSet().stream()
                            .forEach(daira -> {
                                List<Commune> communes = all.stream()
                                                    .filter(distinctByKeys(TmpCitiesPostal::getCommune_name_ascii,TmpCitiesPostal::getDaira_name,TmpCitiesPostal::getWilaya_code))
                                                    .filter(tmpCities -> (wilaya.getWilayaCode().equalsIgnoreCase(tmpCities.getWilaya_code() ) &&
                                                                            daira.getDairaNameFr().equalsIgnoreCase(tmpCities.getDaira_name_ascii())))
                                                    .map(tmpCities -> new Commune(null, tmpCities.getCommune_name_ascii(), tmpCities.getCommune_name(), daira,null))
                                                    .collect(Collectors.toList());
                                daira.setCommunes(new HashSet<>(communes));
                                //dairaRepository.save(daira);
                            });
                    //wilayaRepository.save(wilaya);
                });

        wilayas.stream()
                .forEach(wilaya -> {

                    wilaya.getDairaSet().stream()
                            .forEach(daira -> {
                                daira.getCommunes().stream()
                                        .forEach(commune -> {
                                            List<PostDetail> postDetails = all.stream()
                                                    .filter(tmpCities -> (wilaya.getWilayaCode().equalsIgnoreCase(tmpCities.getWilaya_code() ) &&
                                                            daira.getDairaNameFr().equalsIgnoreCase(tmpCities.getDaira_name_ascii())) &&
                                                            commune.getCommuneNameFr().equalsIgnoreCase(tmpCities.getCommune_name_ascii())
                                                            )
                                                    .map(tmpCities -> new PostDetail(null,tmpCities.getPost_code(),
                                                            tmpCities.getPost_name_ascii(),tmpCities.getPost_name(),
                                                            tmpCities.getPost_address_ascii(),tmpCities.getPost_address(),
                                                            commune
                                                            ))
                                                    .collect(Collectors.toList());
                                            commune.setPostDetails(new HashSet<>(postDetails));

                                        });
                            });
                    wilayaRepository.save(wilaya);
                });

        System.out.println(all.size());

    }


    private static <T> Predicate<T> distinctByKeys(final Function<? super T, ?>... keyExtractors)
    {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t ->
        {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());

            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }

 */
}
