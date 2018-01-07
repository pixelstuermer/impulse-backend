package com.github.pixelstuermer.impulse.backend.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class CounterOperation {

   private Map<String, Object> metaData;
   private Map<String, Object> payload;

   public CounterOperation( String type ) {
      metaData = new HashMap<>();
      setMetaData();
      payload = new HashMap<>();
      setPayload( type );
   }

   private void setMetaData() {
      metaData.put( "timestamp", new Date().toString() );
   }

   private void setPayload( String type ) {
      payload.put( "type", type );
   }

}
