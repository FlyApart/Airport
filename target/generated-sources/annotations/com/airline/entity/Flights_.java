package com.airline.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Flights.class)
public abstract class Flights_ {

	public static volatile SingularAttribute<Flights, Airports> arriveAirport;
	public static volatile SingularAttribute<Flights, Airports> departureAirport;
	public static volatile SingularAttribute<Flights, Double> price;
	public static volatile SingularAttribute<Flights, Airplanes> airplane;
	public static volatile SingularAttribute<Flights, Timestamp> arriveDate;
	public static volatile SingularAttribute<Flights, Airline> airlines;
	public static volatile SetAttribute<Flights, Discounts> discount;
	public static volatile SingularAttribute<Flights, Long> id;
	public static volatile SingularAttribute<Flights, Timestamp> departureDate;
	public static volatile SingularAttribute<Flights, String> fightsNumber;
	public static volatile SingularAttribute<Flights, Timestamp> changed;

	public static final String ARRIVE_AIRPORT = "arriveAirport";
	public static final String DEPARTURE_AIRPORT = "departureAirport";
	public static final String PRICE = "price";
	public static final String AIRPLANE = "airplane";
	public static final String ARRIVE_DATE = "arriveDate";
	public static final String AIRLINES = "airlines";
	public static final String DISCOUNT = "discount";
	public static final String ID = "id";
	public static final String DEPARTURE_DATE = "departureDate";
	public static final String FIGHTS_NUMBER = "fightsNumber";
	public static final String CHANGED = "changed";

}

