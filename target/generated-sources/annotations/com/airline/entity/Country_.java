package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Country.class)
public abstract class Country_ {

	public static volatile SingularAttribute<Country, String> name;
	public static volatile SingularAttribute<Country, String> location;
	public static volatile SingularAttribute<Country, Long> id;
	public static volatile SingularAttribute<Country, Long> population;

	public static final String NAME = "name";
	public static final String LOCATION = "location";
	public static final String ID = "id";
	public static final String POPULATION = "population";

}

