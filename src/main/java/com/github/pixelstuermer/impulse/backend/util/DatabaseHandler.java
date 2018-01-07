package com.github.pixelstuermer.impulse.backend.util;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pixelstuermer.impulse.backend.model.CounterOperation;
import com.mongodb.BasicDBObject;

public final class DatabaseHandler {

   public static void insertCounterDocument( MongoTemplate mongoTemplate, String collectionName, String type )
      throws JsonProcessingException {
      CounterOperation counterOperation = new CounterOperation( type );
      BasicDBObject basicDBObject = BasicDBObject.parse( new ObjectMapper().writeValueAsString( counterOperation ) );
      mongoTemplate.getCollection( collectionName ).insert( basicDBObject );
   }

}
