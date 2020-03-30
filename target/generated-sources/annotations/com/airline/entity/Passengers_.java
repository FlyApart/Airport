package com.airline.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Passengers.class)
public abstract class Passengers_ {

	public static volatile SingularAttribute<Passengers, String> password;
	public static volatile SetAttribute<Passengers, Tickets> tickets;
	public static volatile SingularAttribute<Passengers, Timestamp> created;
	public static volatile SingularAttribute<Passengers, String> name;
	public static volatile SingularAttribute<Passengers, Long> id;
	public static volatile SingularAttribute<Passengers, Countries> countries;
	public static volatile SetAttribute<Passengers, Passports> passports;
	public static volatile SingularAttribute<Passengers, String> login;
	public static volatile SingularAttribute<Passengers, Timestamp> birthDate;
	public static volatile SingularAttribute<Passengers, String> secondName;
	public static volatile SingularAttribute<Passengers, Timestamp> changed;

	public static final String PASSWORD = "password";
	public static final String TICKETS = "tickets";
	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String COUNTRIES = "countries";
	public static final String PASSPORTS = "passports";
	public static final String LOGIN = "login";
	public static final String BIRTH_DATE = "birthDate";
	public static final String SECOND_NAME = "secondName";
	public static final String CHANGED = "changed";

}

