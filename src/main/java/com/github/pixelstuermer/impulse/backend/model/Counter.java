package com.github.pixelstuermer.impulse.backend.model;

import lombok.Getter;

@Getter
public class Counter {

   private int counter;
   private int increaseCounter;
   private int decreaseCounter;

   public Counter( int increaseCounter, int decreaseCounter ) {
      this.increaseCounter = increaseCounter;
      this.decreaseCounter = decreaseCounter;
      setCounter();
   }

   private void setCounter() {
      counter = increaseCounter - decreaseCounter;
   }

}
