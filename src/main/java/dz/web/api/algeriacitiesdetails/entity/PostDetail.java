package dz.web.api.algeriacitiesdetails.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Messaoud GUERNOUTI on 10/26/2023
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PostDetail")
public class PostDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postalCode;
    private String postNameFr;

    private String postNameAr;

    private String postAddressFr;

    private String postAddressAr;

    @JsonBackReference
    @JoinColumn(name = "commune_id")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Commune commune;




}
