package com.airline.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, String> role;
	public static volatile SingularAttribute<Role, Passengers> passengerId;
	public static volatile SingularAttribute<Role, Long> id;

	public static final String ROLE = "role";
	public static final String PASSENGER_ID = "passengerId";
	public static final String ID = "id";

}

