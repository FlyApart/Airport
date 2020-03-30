package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Airlines.class)
public abstract class Airlines_ {

	public static volatile SingularAttribute<Airlines, Long> fleet;
	public static volatile SingularAttribute<Airlines, String> website;
	public static volatile SingularAttribute<Airlines, String> name;
	public static volatile SingularAttribute<Airlines, Long> id;
	public static volatile SingularAttribute<Airlines, Countries> countries;
	public static volatile SingularAttribute<Airlines, Long> flightsCounts;

	public static final String FLEET = "fleet";
	public static final String WEBSITE = "website";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String COUNTRIES = "countries";
	public static final String FLIGHTS_COUNTS = "flightsCounts";

}

