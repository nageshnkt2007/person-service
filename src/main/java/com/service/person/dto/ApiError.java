package com.service.person.dto;


import lombok.Data;

/**
 * The type Api error.
 */
@Data
public class ApiError {

  /**
   * The Status.
   */
  private Integer status;

  /**
   * The Error.
   */
  private String error;

  /**
   * The Message.
   */
  private String message;
}

