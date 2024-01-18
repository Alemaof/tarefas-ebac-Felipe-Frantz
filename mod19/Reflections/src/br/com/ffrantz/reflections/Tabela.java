package br.com.ffrantz.reflections;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tabela {

    //O parâmetro único deve sempre ser value
    String value();

}
