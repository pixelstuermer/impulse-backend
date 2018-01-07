package com.github.pixelstuermer.impulse.backend.model;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DatabaseStats {

   @Field( value = "db" )
   private String name;
   private double avgObjSize;
   private double dataSize;
   private double storageSize;
   @Field( value = "indexes" )
   private int indizes;
   private double indexSize;
   private double fileSize;

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
