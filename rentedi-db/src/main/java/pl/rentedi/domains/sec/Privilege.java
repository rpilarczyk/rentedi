package pl.rentedi.domains.sec;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * List of privileges
 * -
 * */
@Entity
@Table(name = "PRIVILEGES")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Privilege {

    @Id
    @Column(name = "PRIVILEGE_ID", unique = true)
    private String privilegeId;

    @Column(name = "DESCRIPTION")
    private String description;

}
