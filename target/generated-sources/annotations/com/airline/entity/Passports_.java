package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Passports.class)
public abstract class Passports_ {

	public static volatile SingularAttribute<Passports, Long> number;
	public static volatile SingularAttribute<Passports, Passengers> passengersId;
	public static volatile SingularAttribute<Passports, Long> series;
	public static volatile SingularAttribute<Passports, Long> id;
	public static volatile SingularAttribute<Passports, String> title;

	public static final String NUMBER = "number";
	public static final String PASSENGERS_ID = "passengersId";
	public static final String SERIES = "series";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

