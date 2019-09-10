package pl.rentedi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rentedi.domains.sec.Privilege;
import pl.rentedi.domains.sec.Role;
import pl.rentedi.domains.sec.User;
import pl.rentedi.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    //@Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException, DataAccessException {

        final User user = usersRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " not found");
        }


        final Set<Role> roles = user.getRoles();
        final Collection<GrantedAuthority> authorities = getAuthorities(roles);

        for (Role role : roles) {
            Set<Privilege> privileges = role.getPrivileges();
            for (Privilege privilege : privileges) {
                authorities.add(new SimpleGrantedAuthority(privilege.getPrivilegeId()));
            }
        }

        //throw new UserNotAuthorizedException("User " + userName + " not authorized");
        User.Status status = user.getStatus();

        return new ContextUser(user.getUserId(), user.getUsername(), user.getPassword(),
                status.equals(User.Status.ACCEPTED) || status.equals(User.Status.ACTIVE),
                !status.equals(User.Status.EXPIRED), true,
                !status.equals(User.Status.LOCKED), authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(final Set<Role> roles) {
        final List<GrantedAuthority> authorityList = new ArrayList<>();

        for (Role role : roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleId()));
        }

        logger.debug("authorityList:" + authorityList);

        return authorityList;
    }

}
