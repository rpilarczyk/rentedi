package pl.rentedi.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class ContextUser extends User {
    private final Long userId;

    public ContextUser(final Long userId, final String username, final String password,
                       final boolean enabled,
                       final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked,
                       final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

}
