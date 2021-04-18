package au.com.belong.customermaster.contactpoints.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class TimeLogger2 {

    @Pointcut("execution(* au.com.belong.customermaster.contactpoints.service..*(..))")
    private void anyOperationInControllerPackage() {
        /* nothing to do here;
         * this just defines that we want to catch all methods
         * in the controller-package
         */
    }

    @Around("anyOperationInControllerPackage()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Entering " + joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName() + "() using arguments: " + Arrays.toString( joinPoint.getArgs() ) );

        try {

            Object result = joinPoint.proceed();

            System.out.println("Leaving " + joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName() + "()." );

            return result;

        } catch (Throwable ex) {

            ex.printStackTrace();
            throw ex;

        }

    }
}
