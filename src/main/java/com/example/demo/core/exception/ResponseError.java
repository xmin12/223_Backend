package com.example.demo.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

// Serializable class representing an error response
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ResponseError implements Serializable {

  // Timestamp of when the error occurred
  private LocalDate timeStamp;

  // Map of errors containing field name and error message pairs
  private Map<String, String> errors;

  // Method to build and return the ResponseError object
  public ResponseError build() {
    return this;
  }
}
