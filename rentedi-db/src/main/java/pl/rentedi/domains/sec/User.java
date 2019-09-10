package pl.rentedi.domains.sec;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/*
 * List of users
 * - The status indicates the state in which the account is located [ UNDEFINED, ACCEPTED, LOCKED, ACTIVE, INACTIVE, SUSPENDED, DELETED ]
 * - Each user has [is] the personEntity
 * */

@javax.persistence.Entity
@Table(name = "USERS", indexes = {@Index(name = "idx_user_id", columnList = "USER_ID")})
@Setter
@Getter
@ToString(exclude = {"personEntity", "roles", "privilegesUsage"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    @javax.persistence.SequenceGenerator(
            name = "SEQ_USERS",
            sequenceName = "SEQ_USERS",
            allocationSize = 1)
    @Column(name = "USER_ID", unique = true)
    private Long userId;

    @Column(name = "USERNAME", unique = true)
    private String username;

  //  @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private String oldPassword;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<PrivilegeUsage> privilegesUsage;

    /**
     * Undefined
     * Accepted
     */

    public enum Status {
        UNDEFINED, ACCEPTED, ACTIVE, INACTIVE, LOCKED, SUSPENDED, DELETED, REGISTER, EXPIRED
    }

}
