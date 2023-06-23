package etu1830.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // durée de vie ( execution )
@Target(ElementType.PARAMETER) // peut etre utilisee sur les arguments de fonction
public @interface Args{
    String argName() default "";
}
