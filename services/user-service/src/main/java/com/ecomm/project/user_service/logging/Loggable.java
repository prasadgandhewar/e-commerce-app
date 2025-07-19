package com.ecomm.project.user_service.logging;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})  // Support both class & method
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Loggable {}
