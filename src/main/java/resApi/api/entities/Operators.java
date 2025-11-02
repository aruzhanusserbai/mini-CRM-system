package resApi.api.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "operators")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String department;

    @ManyToMany
    @JoinTable(
            name = "operators_requests",
            joinColumns = @JoinColumn(name = "operators_id"),
            inverseJoinColumns = @JoinColumn(name = "applicationRequest_id")
    )
    private List<ApplicationRequest> requests = new ArrayList<>();
}
