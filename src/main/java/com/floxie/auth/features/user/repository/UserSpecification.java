package com.floxie.auth.features.user.repository;

import com.floxie.auth.features.user.dto.UserFilter;
import com.floxie.auth.features.user.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {

  private final UserFilter filter;

  public UserSpecification(UserFilter filter) {
    this.filter = filter;
  }

  @Override
  public Predicate toPredicate(
      @NonNull Root<User> root,
      @NonNull CriteriaQuery<?> query,
      @NonNull CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();

    if (filter.username() != null) {
      predicates.add(criteriaBuilder.like(root.get("username"), "%" + filter.username() + "%"));
    }
    if (filter.email() != null) {
      predicates.add(criteriaBuilder.like(root.get("email"), "%" + filter.email() + "%"));
    }
    if (filter.role() != null) {
      predicates.add(criteriaBuilder.equal(root.get("role"), filter.role()));
    }

    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}
