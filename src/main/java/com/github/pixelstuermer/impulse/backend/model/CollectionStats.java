package com.github.pixelstuermer.impulse.backend.model;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CollectionStats {

   @Field( value = "ns" )
   private String name;
   private int count;
   private double avgObjSize;
   private double storageSize;
   @Field( value = "nindexes" )
   private int indizes;
   private double totalIndexSize;

   @Override
   public String toString() {
      try {
         return new ObjectMapper().writeValueAsString( this );
      }
      catch ( JsonProcessingException e ) {
         return super.toString();
      }
   }

}
