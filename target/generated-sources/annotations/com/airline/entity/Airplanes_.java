package com.airline.entity;

import java.sql.Time;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Airplanes.class)
public abstract class Airplanes_ {

	public static volatile SingularAttribute<Airplanes, Timestamp> built;
	public static volatile SingularAttribute<Airplanes, Time> flightDuration;
	public static volatile SingularAttribute<Airplanes, String> model;
	public static volatile SingularAttribute<Airplanes, Long> id;
	public static volatile SingularAttribute<Airplanes, Long> seats;

	public static final String BUILT = "built";
	public static final String FLIGHT_DURATION = "flightDuration";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String SEATS = "seats";

}

