package dz.web.api.algeriacitiesdetails.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Messaoud GUERNOUTI on 10/25/2023
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Commune")
@JsonFilter("detailsFilter")
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String communeNameFr;
    private String communeNameAr;

    @JsonBackReference
    @JoinColumn(name = "daira_id")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Daira daira;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "commune")
    private Set<PostDetail> postDetails = new HashSet<>();
}
