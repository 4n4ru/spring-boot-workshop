package dev.runje.ana.springbootworkshop.dao;

import dev.runje.ana.springbootworkshop.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeSearchDao {

  private final EntityManager em;

  public List<Employee> findAllBySimpleQuery(String firstName, String lastName, String email) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

    // SELECT * FROM employee
    Root<Employee> root = criteriaQuery.from(Employee.class);

    // prepare WHERE clause
    // WHERE first_name like '%ana%'
    Predicate firstNamePredicate = criteriaBuilder
        .like(root.get("first_name"), "%" + firstName + "%");
    Predicate lastNamePredicate = criteriaBuilder
        .like(root.get("last_name"), "%" + lastName + "%");
    Predicate emailPredicate = criteriaBuilder
        .like(root.get("email"), "%" + email + "%");

    Predicate firstNameOrLastNamePredicate = criteriaBuilder.or(firstNamePredicate, lastNamePredicate);

    // => final query ==> SELECT * FROM employee where first_name like '%ana%'
    // or last_name like '%run%' and email like '%@live%'
    var andEmailPredicate = criteriaBuilder.and(firstNameOrLastNamePredicate, emailPredicate);
    criteriaQuery.where(andEmailPredicate);

    TypedQuery<Employee> query = em.createQuery(criteriaQuery);
    return query.getResultList();
  }

  public List<Employee> findAllByCriteria(SearchRequest request) {

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
    List<Predicate> predicates = new ArrayList<>();

    // select from employee
    Root<Employee> root = criteriaQuery.from(Employee.class);

    if (request.getFirstName() != null){
      Predicate firstNamePredicate = criteriaBuilder.like(root.get("first_name"), "%" + request.getFirstName() + "%");
      predicates.add(firstNamePredicate);
    }

    if (request.getLastName() != null){
      Predicate lastNamePredicate = criteriaBuilder.like(root.get("last_name"), "%" + request.getLastName() + "%");
      predicates.add(lastNamePredicate);
    }

    if (request.getEmail() != null){
      Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%");
      predicates.add(emailPredicate);
    }

    criteriaQuery.where(
        criteriaBuilder.or(predicates.toArray(new Predicate[0]))
    );

    TypedQuery<Employee> query = em.createQuery(criteriaQuery);
    return query.getResultList();
  }

}
