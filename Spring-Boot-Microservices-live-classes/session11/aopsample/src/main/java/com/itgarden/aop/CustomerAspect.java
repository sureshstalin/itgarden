package com.itgarden.aop;

import com.itgarden.entity.Customer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class CustomerAspect {

    Logger logger = LoggerFactory.getLogger(CustomerAspect.class);

    /*
        Aspect
        Using Aspect we implement the common task generically
        Pointcut - execution(* com.itgarden.service.*.*(..))
        It is an expression that will say what is the methods need to execute from a particular class or package
        Advice - Before , After and around
        Using the advice we can tell when a particular joinpoint's metadata should execute either Before, After, or around
        JoinPoint
        based on Pointcut expression AOP loads and keep the metadata in the JoinPoint object

        Around

     */
    @Before("execution(* com.itgarden.service.CustomerService.*(..))") // pointcut
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println(joinPoint + "::" + joinPoint.getSignature().getName() + "::starts");
//        logger.info(joinPoint + "::" + joinPoint.getSignature().getName() + "::starts");
        Object[] args = joinPoint.getArgs();
        for (Object argument : args) {
            System.out.println("The Argument is  " + argument);
        }
//        System.out.println("before method");
    }

//    @Before("execution(* com.itgarden.service.*.*(..))") // pointcut
//    public void beforeMethod(JoinPoint joinPoint) {
//        System.out.println(joinPoint + "::" + joinPoint.getSignature().getName() + "::starts");
//        logger.info(joinPoint + "::" + joinPoint.getSignature().getName() + "::starts");
//        Object[] args = joinPoint.getArgs();
//        for (Object argument: args) {
//            System.out.println("The Argument is  " + argument);
//        }
//        System.out.println("before method");
//    }

    @After("execution(* com.itgarden.service.*.*(..))") // pointcut
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("After Method ");
        System.out.println(joinPoint + "::" + joinPoint.getSignature().getName() + "::ends");
    }

    @Around("execution(* com.itgarden.service.*.*(..))")
    public void aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        joinPoint.proceed();
        System.out.println("Around " + joinPoint + "::" + joinPoint.getSignature().getName());
    }


//    @AfterReturning(value = "execution(* com.itgarden.service.*.*(..))", returning = "result") // pointcut
//    public void afterMethodReturning(JoinPoint joinPoint, Object result) {
//        System.out.println(joinPoint + "::" + joinPoint.getSignature().getName() + "::ends");
//        if (result instanceof Customer) {
//            Customer customer = (Customer) result;
//            System.out.println("Name " + ((Customer) result).getName());
//        } else {
//            List<Customer> customers = (List<Customer>) result;
//            customers.stream().forEach(customer -> System.out.println(customer.getName()));
//        }
//    }
}
