package sit.integrated.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sit.integrated.project.models.JwtUser;
import sit.integrated.project.repositories.JwtUserRepositories;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    JwtUserRepositories userRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        JwtUser user = userRepository.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username));
        return user;
    }
}
