package com.work.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan("com.work.manager")
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
public class Catcus
{
    public static void main( String[] args )
    {
    	SpringApplication.run(Catcus.class, args);
        System.err.println("start");
        
    }
    
}
