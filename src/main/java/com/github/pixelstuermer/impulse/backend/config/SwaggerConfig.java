package com.github.pixelstuermer.impulse.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

   private final Logger LOGGER = LoggerFactory.getLogger( this.getClass() );

   @Value( "${project.version}" )
   private String projectVersion;

   @Value( "${project.name}" )
   private String projectName;

   @Value( "${project.description}" )
   private String projectDescription;

   @Value( "${project.author}" )
   private String projectAuthor;

   @Value( "${project.author.url}" )
   private String projectAuthorUrl;

   @Value( "${project.license}" )
   private String projectLicense;

   @Value( "${project.license.url}" )
   private String projectLicenseUrl;

   @Bean
   public Docket swaggerApi( @Value( "${application.base.packages}" ) String basePackages ) {
      LOGGER.info( "Generated SwaggerUI API" );
      return new Docket( DocumentationType.SWAGGER_2 ).select()
         .apis( RequestHandlerSelectors.basePackage( basePackages ) )
         .paths( PathSelectors.any() )
         .paths( excludePaths() )
         .build()
         .apiInfo( getSwaggerMetaData() );
   }

   @SuppressWarnings( "deprecation" )
   private ApiInfo getSwaggerMetaData() {
      LOGGER.info( "Generated SwaggerUI metadata" );
      ApiInfo apiInfo = new ApiInfo( projectName, projectDescription, projectVersion, projectLicenseUrl,
         projectAuthorUrl, projectLicense, projectLicenseUrl );
      return apiInfo;
   }

   private Predicate<String> excludePaths() {
      LOGGER.info( "Generated SwaggerUI exclusion paths" );
      // exclude the SwaggerUI redirect controller
      return Predicates.not( PathSelectors.regex( "/" ) );
   }

}
