package dev.runje.ana.springbootworkshop.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {

  private String firstName;
  private String lastName;
  private String email;

}
