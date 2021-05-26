package berszam.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Dolgozo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nev;

    @Column(nullable = false)
    private String pozicio;

    @Column(nullable = false)
    private int alapber;

    public String toString() {
        return nev + " (id: " + id + ")";
    }


}
