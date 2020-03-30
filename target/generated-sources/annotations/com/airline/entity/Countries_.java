package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Countries.class)
public abstract class Countries_ {

	public static volatile SingularAttribute<Countries, String> name;
	public static volatile SingularAttribute<Countries, String> location;
	public static volatile SingularAttribute<Countries, Long> id;
	public static volatile SingularAttribute<Countries, Long> population;

	public static final String NAME = "name";
	public static final String LOCATION = "location";
	public static final String ID = "id";
	public static final String POPULATION = "population";

}

