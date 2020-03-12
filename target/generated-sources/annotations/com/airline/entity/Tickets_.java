package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tickets.class)
public abstract class Tickets_ {

	public static volatile SetAttribute<Tickets, Passengers> passengers;
	public static volatile SingularAttribute<Tickets, Double> totalPrice;
	public static volatile SingularAttribute<Tickets, Boolean> reservation;
	public static volatile SingularAttribute<Tickets, Long> id;
	public static volatile SingularAttribute<Tickets, String> place;
	public static volatile SingularAttribute<Tickets, Flights> flights;

	public static final String PASSENGERS = "passengers";
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String RESERVATION = "reservation";
	public static final String ID = "id";
	public static final String PLACE = "place";
	public static final String FLIGHTS = "flights";

}

