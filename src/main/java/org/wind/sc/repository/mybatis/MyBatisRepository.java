package org.wind.sc.repository.mybatis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;


/**
 * it's good for scannning the mybatis Dao class by MapperScannerConfigurer
 * @author stephen
 *
 */
@Retention(RetentionPolicy.RUNTIME)//虚拟机在运行时会对其读取
@Target(ElementType.TYPE)//表示注解应用的类型，是类,（ElementType.method是方法）
@Documented //加入javadoc中
@Component
public @interface MyBatisRepository {
	String values() default "";
}
