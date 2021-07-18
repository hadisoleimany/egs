package com.shop.egs.aop;

import com.shop.egs.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class AccessAspect {
    @Around("@annotation(AccessibleUser)")
    public Object changeDataSchema(ProceedingJoinPoint joinPoint) throws Throwable {
        Optional<? extends GrantedAuthority> first = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst();
        if (first.isPresent() && first.get().getAuthority().equalsIgnoreCase("ROLE_USER")) {
            throw new BusinessException("You Don't Have Permission");
        }
        return joinPoint.proceed();
    }
}
