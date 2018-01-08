package com.github.pixelstuermer.impulse.backend.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Download {

   private List<CounterOperation> counterOperationList;
   private Counter counter;

}
