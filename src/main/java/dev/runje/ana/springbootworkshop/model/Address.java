package dev.runje.ana.springbootworkshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

  @Id
  @GeneratedValue
  private Integer id;

  private String streetName;

  private String houseNumber;

  private String zipCode;

}
