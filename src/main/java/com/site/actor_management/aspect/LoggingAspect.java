package com.site.actor_management.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class LoggingAspect {

    // CONCEPT 1: POINTCUT EXPRESSIONS & COMBINATIONS (execution, within, &&, !)

    @Pointcut("within(com.site.actor_management.controller..*)")
    public void inControllerLayer() {}

    @Pointcut("execution(* com.site.actor_management.controller.*.get*(..))")
    public void getterMethods() {}

    @Pointcut("inControllerLayer() && !getterMethods()")
    public void writeOperations() {}

    @Pointcut("@annotation(com.site.actor_management.aspect.TrackExecution)")
    public void trackedMethods() {}

    // CONCEPT 2: joinpoint intersection & @before advice
    @Before("inControllerLayer()")
    public void logBeforeWithDetails(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs(); // Captures arguments passed to the CRUD method

        System.out.println(">>> [AOP @Before] Class: " + className
                + " | Method: " + methodName
                + " | Arguments: " + Arrays.toString(args));
    }

    // CONCEPT 3: @AfterReturning (Captures return value)
    @AfterReturning(pointcut = "inControllerLayer()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println(">>> [AOP @AfterReturning] " + joinPoint.getSignature().getName()
                + " succeeded | Returned: " + result);
    }



    // CONCEPT 4: @AfterThrowing (Handles exceptions cleanly)
    @AfterThrowing(pointcut = "inControllerLayer()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println(">>> [AOP @AfterThrowing] Exception in " + joinPoint.getSignature().getName()
                + " | Cause: " + ex.getMessage());
    }


    // CONCEPT 5: @After (Guaranteed execution, like finally)
    @After("inControllerLayer()")
    public void logAfterFinally(JoinPoint joinPoint) {
        System.out.println(">>> [AOP @After] Completed lifecycle of: " + joinPoint.getSignature().getName());
    }


    // CONCEPT 6: @Around ADVICE (Performance Profiling & Execution Control)
    @Around("inControllerLayer()")
    public Object profileExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed(); // Allows the target method to execute

        long duration = System.currentTimeMillis() - start;
        System.out.println(">>> [AOP @Around] Execution time of " + joinPoint.getSignature().getName() + ": " + duration + " ms");
        return result;
    }


    // CONCEPT 7: TARGETED ADVICE USING COMBINED & CUSTOM ANNOTATION POINTCUTS
    @Before("writeOperations()")
    public void auditDataModification(JoinPoint joinPoint) {
        System.out.println(">>> [AOP AUDIT - Combined Pointcut] Modifying data via: " + joinPoint.getSignature().getName());
    }

    @Before("trackedMethods()")
    public void logCustomAnnotation(JoinPoint joinPoint) {
        System.out.println(">>> [AOP @TrackExecution] Custom annotated method triggered: " + joinPoint.getSignature().getName());
    }
}