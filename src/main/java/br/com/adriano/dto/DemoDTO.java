package br.com.adriano.dto;

import java.util.Date;

public class DemoDTO {

  public String name;
  public Date date;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Name: " + this.name + ", Date: " + this.date;
  }
}
