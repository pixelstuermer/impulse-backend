package com.github.pixelstuermer.impulse.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( path = "/" )
@Api( "Base controller" )
public class BaseController {

   private final Logger LOGGER = LoggerFactory.getLogger( this.getClass() );

   @RequestMapping( path = "/", method = RequestMethod.GET )
   @ApiOperation( value = "Redirect to SwaggerUI", hidden = true )
   public RedirectView redirectSwagger( @Value( "${server.contextPath}" ) String contextPath ) {
      LOGGER.info( "Request for ErrorPathController, so redirect to SwaggerUI" );
      return new RedirectView( contextPath + "/swagger-ui.html" );
   }

   @RequestMapping( path = "/ping", method = RequestMethod.GET )
   @ApiOperation( value = "Ping method" )
   public ResponseEntity<String> getPing() {
      LOGGER.info( "Request for PingController" );
      return ResponseEntity.status( 200 ).body( "Hello World" );
   }

}
