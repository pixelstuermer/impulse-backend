package com.github.pixelstuermer.impulse.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan( basePackages = "${application.base.packages}" )
public class ApplicationStarter {

   public static void main( String[] args ) {
      SpringApplication.run( ApplicationStarter.class, args );
   }

}
