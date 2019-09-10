package pl.rentedi.domains.sec;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ROLES")
@Setter
@Getter
@ToString(exclude = {"privileges"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @Column(name = "ROLE_ID", unique = true)
    private String roleId;

    @Column(name = "DESCRIPTION")
    private String description;

    //, CascadeType.MERGE, CascadeType.DETACH ?
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "ROLES_PRIVILEGES",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID"))
    private Set<Privilege> privileges;
}
