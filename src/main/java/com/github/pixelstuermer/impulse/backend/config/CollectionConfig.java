package com.github.pixelstuermer.impulse.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pixelstuermer.impulse.backend.model.Collection;

@Configuration
public class CollectionConfig {

   @Bean
   public Collection collection( @Value( "${application.collection.name}" ) String collectionName ) {
      return Collection.builder().name( collectionName ).build();
   }

}
