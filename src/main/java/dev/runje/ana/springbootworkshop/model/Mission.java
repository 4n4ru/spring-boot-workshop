package dev.runje.ana.springbootworkshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Mission {

  @Id
  @GeneratedValue
  private Integer id;

  private String name;

  private int duration;

  @ManyToMany (mappedBy = "missions")
  private List<Employee> employees;

}
