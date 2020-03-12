package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Airline.class)
public abstract class Airline_ {

	public static volatile SingularAttribute<Airline, Long> fleet;
	public static volatile SingularAttribute<Airline, Country> country;
	public static volatile SingularAttribute<Airline, String> website;
	public static volatile SingularAttribute<Airline, String> name;
	public static volatile SingularAttribute<Airline, Long> id;
	public static volatile SingularAttribute<Airline, Long> flightsCounts;

	public static final String FLEET = "fleet";
	public static final String COUNTRY = "country";
	public static final String WEBSITE = "website";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String FLIGHTS_COUNTS = "flightsCounts";

}

