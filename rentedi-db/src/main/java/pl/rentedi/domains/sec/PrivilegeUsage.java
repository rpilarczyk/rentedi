package pl.rentedi.domains.sec;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRIVILEGES_USAGE")
@Setter
@Getter
@ToString(exclude = {"user", "privilege"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrivilegeUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRIVILEGES_USAGE")
    @javax.persistence.SequenceGenerator(
            name = "SEQ_PRIVILEGES_USAGE",
            sequenceName = "SEQ_PRIVILEGES_USAGE",
            allocationSize = 1)

    @Column(name = "PRIVILEGE_USAGE_ID", unique = true)
    private Long privilegeUsageId;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "PRIVILEGE_ID")
    private Privilege privilege;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "QUANTITY_LEFT")
    private BigDecimal quantityLeft;

    @Column(name = "UNIT", nullable = false)
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Column(name = "ACCESS")
    private boolean access;

    public enum Unit {
        ITEM, TIME
    }

}
