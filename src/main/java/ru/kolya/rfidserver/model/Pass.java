package ru.kolya.rfidserver.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "passes")
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Setter
    private LocalDateTime come;

    @Setter
    private LocalDateTime gone;

    public Pass(User user, LocalDateTime come) {
        this.user = user;
        this.come = come;
    }
}
