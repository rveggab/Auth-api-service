package io.github.rveggab.auth.infrastructure.adapter.out.persistence;

import io.github.rveggab.auth.domain.model.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "iam", name = "role", uniqueConstraints = @UniqueConstraint(
        columnNames = {"app_id", "type"}))
@Setter
@Getter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserRoles roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    private AppEntity app;
}
