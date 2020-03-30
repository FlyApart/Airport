package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Discounts.class)
public abstract class Discounts_ {

	public static volatile SingularAttribute<Discounts, Double> cost;
	public static volatile SingularAttribute<Discounts, Long> id;
	public static volatile SingularAttribute<Discounts, String> title;
	public static volatile SetAttribute<Discounts, Flights> flights;

	public static final String COST = "cost";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String FLIGHTS = "flights";

}

