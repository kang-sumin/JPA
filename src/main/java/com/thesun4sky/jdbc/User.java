package com.thesun4sky.jdbc;


public class User {

  private Integer id;

  private String name;


  public User() {
  }

  public User(String name) {
    this.name = name;
  }

  public String toString() {
    return "User(id:" + id + ", name:" + name + ")";
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
