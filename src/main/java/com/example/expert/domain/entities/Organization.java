package com.example.expert.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class Organization.
 *
 * @author Vladimir Goncharenko
 * @email vldmrgon@gmail.com
 * @since 07/11/2021
 */

@Entity
public class Organization {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
}
