package dev.runje.ana.springbootworkshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Department {

  @Id
  @GeneratedValue
  private Integer id;

  private String name;

  @OneToMany(mappedBy = "department")
  private List<Employee> employees;

}
