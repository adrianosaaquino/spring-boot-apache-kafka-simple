package br.com.adriano.dto;

import java.util.Date;

public class Demo2DTO {

  public String name;
  public Date date;
  // public boolean enabled;

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

  /*public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }*/

  @Override
  public String toString() {
    return "Name: " + this.name + ", Date: " + this.date;
  }

  /*@Override
  public String toString() {
    return "Name: " + this.name + ", Date: " + this.date + ", Enabled: " + this.enabled;
  }*/
}
