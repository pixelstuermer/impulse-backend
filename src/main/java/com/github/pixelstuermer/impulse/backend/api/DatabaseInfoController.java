package com.github.pixelstuermer.impulse.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pixelstuermer.impulse.backend.model.Collection;
import com.github.pixelstuermer.impulse.backend.model.CollectionStats;
import com.github.pixelstuermer.impulse.backend.model.DatabaseStats;
import com.github.pixelstuermer.impulse.backend.model.MongoInfo;
import com.mongodb.BasicDBObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( path = "/mongo" )
@Api( "Mongo info controller" )
public class DatabaseInfoController {

   private final Logger LOGGER = LoggerFactory.getLogger( this.getClass() );

   @Autowired
   MongoTemplate mongoTemplate;

   @Autowired
   Collection collection;

   @RequestMapping( path = "/info", method = RequestMethod.GET )
   @ApiOperation( value = "Mongo info" )
   public ResponseEntity<MongoInfo> getMongoInfo() {
      LOGGER.info( "Request for MongoInfoController" );
      MongoInfo mongoInfo = MongoInfo.builder()
         .dbName( mongoTemplate.getDb().getName() )
         .collectionName( collection.getName() )
         .build();
      return ResponseEntity.status( 200 ).body( mongoInfo );
   }

   @RequestMapping( path = "/db-stats", method = RequestMethod.GET )
   @ApiOperation( value = "Database stats" )
   public ResponseEntity<DatabaseStats> getDatabaseStats() {
      BasicDBObject basicDBObject = mongoTemplate.getDb().getStats();
      DatabaseStats databaseStats = mongoTemplate.getConverter().read( DatabaseStats.class, basicDBObject );
      LOGGER.info( "Request for DatabaseStatsController: {}", databaseStats.toString() );
      return ResponseEntity.status( 200 ).body( databaseStats );
   }

   @RequestMapping( path = "/collection-stats", method = RequestMethod.GET )
   @ApiOperation( value = "Collection stats" )
   public ResponseEntity<CollectionStats> getCollectionStats() {
      BasicDBObject basicDBObject = mongoTemplate.getCollection( collection.getName() ).getStats();
      CollectionStats collectionStats = mongoTemplate.getConverter().read( CollectionStats.class, basicDBObject );
      LOGGER.info( "Request for CollectionStatsController: {}", collectionStats.toString() );
      return ResponseEntity.status( 200 ).body( collectionStats );
   }

}
