package classes.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class DetermineTargetUrl {
    public static String determineTargetUrl(Authentication authentication) {
        boolean isStudent = false;
        boolean isAdmin = false;
        boolean isHeadMaster = false;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_STUDENT")) {
                isStudent = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_HEAD_MASTER")) {
                isHeadMaster = true;
                break;
            }
        }

        if (isStudent) {
            return "student";
        } else if (isAdmin) {
            return "students";
        } else if (isHeadMaster) {
            return "students";
        } else {
            throw new IllegalStateException();
        }
    }
}
