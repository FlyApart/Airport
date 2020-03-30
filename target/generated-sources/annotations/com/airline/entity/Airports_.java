package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Airports.class)
public abstract class Airports_ {

	public static volatile SingularAttribute<Airports, Long> id;
	public static volatile SingularAttribute<Airports, Countries> countries;
	public static volatile SingularAttribute<Airports, String> title;

	public static final String ID = "id";
	public static final String COUNTRIES = "countries";
	public static final String TITLE = "title";

}

