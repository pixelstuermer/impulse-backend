package com.github.pixelstuermer.impulse.backend.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pixelstuermer.impulse.backend.constants.MongoConstants;
import com.github.pixelstuermer.impulse.backend.model.Collection;
import com.github.pixelstuermer.impulse.backend.model.Counter;
import com.github.pixelstuermer.impulse.backend.model.CounterOperation;
import com.github.pixelstuermer.impulse.backend.model.Download;
import com.github.pixelstuermer.impulse.backend.util.DatabaseHandler;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( path = "/counter" )
@Api( "Conter controller" )
public class CounterController {

   private final Logger LOGGER = LoggerFactory.getLogger( this.getClass() );

   @Autowired
   MongoTemplate mongoTemplate;

   @Autowired
   Collection collection;

   @RequestMapping( path = "", method = RequestMethod.GET )
   @ApiOperation( value = "Get the calculated counter" )
   public ResponseEntity<Counter> getCaclculatedCounter() {
      int increaseCounter = DatabaseHandler
         .getCounterDocuments( mongoTemplate, collection.getName(), MongoConstants.INCREASE );
      int decreaseCounter = DatabaseHandler
         .getCounterDocuments( mongoTemplate, collection.getName(), MongoConstants.DECREASE );
      Counter counter = new Counter( increaseCounter, decreaseCounter );
      LOGGER.info( "Request for GetCaclculatedCounter with {} - {} = {}",
         counter.getIncreaseCounter(), counter.getDecreaseCounter(), counter.getCounter() );
      return ResponseEntity.status( 200 ).body( counter );
   }

   @RequestMapping( path = "/increase", method = RequestMethod.POST )
   @ApiOperation( value = "Increase the impulse counter with +1" )
   public ResponseEntity<?> increaseCounter() {
      try {
         DatabaseHandler.insertCounterDocument( mongoTemplate, collection.getName(), MongoConstants.INCREASE );
         LOGGER.info( "Request for IncreaseCounterController with +1" );
         return ResponseEntity.status( 200 ).build();
      }
      catch ( JsonProcessingException e ) {
         LOGGER.error( "Request for IncreaseCounterController with +1 failed" );
         return ResponseEntity.status( 400 ).build();
      }
   }

   @RequestMapping( path = "/decrease", method = RequestMethod.POST )
   @ApiOperation( value = "Decrease the impulse counter with -1" )
   public ResponseEntity<?> decreaseCounter() {
      try {
         DatabaseHandler.insertCounterDocument( mongoTemplate, collection.getName(), MongoConstants.DECREASE );
         LOGGER.info( "Request for DecreaseCounterController with -1" );
         return ResponseEntity.status( 200 ).build();
      }
      catch ( JsonProcessingException e ) {
         LOGGER.error( "Request for DecreaseCounterController with -1 failed" );
         return ResponseEntity.status( 400 ).build();
      }
   }

   @RequestMapping( path = "/download", method = RequestMethod.GET, produces = "application/json" )
   @ApiOperation( value = "Download the counter documents" )
   public ResponseEntity<Download> downloadCounterDocuments( HttpServletResponse response )
      throws IOException {
      // run mongo query and convert to pojo
      ObjectMapper mapper = new ObjectMapper();
      DBCursor cursor = mongoTemplate.getCollection( collection.getName() ).find();
      List<CounterOperation> counterOperationList = new ArrayList<>();
      while ( cursor.hasNext() ) {
         BasicDBObject document = (BasicDBObject) cursor.next();
         String documentAsString = document.toJson();
         CounterOperation counterOperation = mapper.readValue( documentAsString, CounterOperation.class );
         counterOperationList.add( counterOperation );
      }

      // get counters
      Counter counter = getCaclculatedCounter().getBody();

      // set response headers
      String filename = "counter-documents-" + new Date().getTime();
      response.addHeader( "Content-disposition", "attachment;filename=" + filename + ".json" );
      response.setContentType( "application/json" );

      // prepare download
      Download download = Download.builder()
         .counter( counter )
         .counterOperationList( counterOperationList ).build();

      return ResponseEntity.status( 200 ).body( download );
   }

}
