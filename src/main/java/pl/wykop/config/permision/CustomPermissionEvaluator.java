package pl.wykop.config.permision;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by mariusz on 25.03.17.
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        if (authentication == null) {
            return false;
        }
        authentication.getAuthorities().forEach(System.out::println);
        System.out.println(target);
        System.out.println(permission);
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
