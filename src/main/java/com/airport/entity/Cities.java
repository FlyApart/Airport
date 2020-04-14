package com.airport.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","countries"})
@ToString (exclude = {"countries"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cities")
public class Cities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	Long id;

	@Column(nullable = false, length = 50)
	String name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	Countries countries;


}
