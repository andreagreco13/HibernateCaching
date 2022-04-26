package com.andreagreco.Caching;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Person {
	@Id
	private int pid;
	private PersonName name;
	private String occupation;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public PersonName getName() {
		return name;
	}
	public void setName(PersonName name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
}
