package com.github.pixelstuermer.impulse.backend.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MongoInfo {

   private String dbName;
   private String collectionName;

}
