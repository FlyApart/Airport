package com.airport.repository.springdata;


import com.airport.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface FlightsRepository extends CrudRepository<Flights, Long>, JpaRepository<Flights, Long> {

	Optional<List<Flights>> findByIdIn (Collection<Long> ids);

	Optional<Flights> findByFightsNumber (String fightsNumber);

	@Query("select f from Flights f  " +
			       "join f.arriveAirport as arrCity " +
			       "join f.departureAirport as depCity " +
			       "where upper(arrCity.cities.name) like upper(:arriveCity)  " +
			       "and upper(depCity.cities.name) like upper(:departureCity)  "+
			       "and f.arriveDate = :arriveDate " +
			       "and f.departureDate = :departureDate ")
	Optional<List<Flights>> findWithAllParam (
			String arriveCity,
			String departureCity,
			LocalDate arriveDate,
			LocalDate departureDate);

	@Query("select f from Flights f  " +
			       "join f.arriveAirport as arrCity " +
			       "join f.departureAirport as depCity " +
			       "where upper(arrCity.cities.name) like upper(:arriveCity)  " +
			       "and upper(depCity.cities.name) like upper(:departureCity)  "+
			       "and f.departureDate = :departureDate")
	Optional<List<Flights>> findWithNullArriveDateParam (
			String arriveCity,
			String departureCity,
			LocalDate departureDate);

	@Query("select f from Flights f  " +
			       "join f.departureAirport as depCity " +
			       "where upper(depCity.cities.name) like upper(:departureCity)  "+
			       "and f.arriveDate = :arriveDate " +
			       "and f.departureDate = :departureDate")
	Optional<List<Flights>> findWithAnyArriveCitiesParam (
			String departureCity,
			LocalDate arriveDate,
			LocalDate departureDate);

	@Query("select f from Flights f  " +
			       "join f.departureAirport as depCity " +
			       "where upper(depCity.cities.name) like upper(:departureCity)  "+
			       "and f.departureDate = :departureDate")
	Optional<List<Flights>> findWithAnyArriveCitiesAndNullArriveDateParam (
			String departureCity,
			LocalDate departureDate);

	@Query("select tic.place from Flights f join f.ticket tic where f.id = :id order by tic.place")
	Optional<List<String>> findAllOccupiedSeats (Long id);


	/*@Query("SELECT d.id as departement_id, COUNT(m.id) as nbMateriel FROM Departement d " +
			       "LEFT JOIN d.sites s " +
			       "LEFT JOIN s.materiels m " +
			       "WHERE "
			       + "(s.metier.id IN (:metier_id) OR :metier_id IS NULL) AND " +
			       "(s.entite.id IN (:entite_id) OR :entite_id IS NULL) "
			       + "AND (m.materielType.id IN (:materielType_id) OR :materielType_id IS NULL) AND "
			       + "(d.id= :departement_id OR :departement_id IS NULL) "
			       + "AND m.dateLivraison is not null  and (EXTRACT(YEAR FROM m.dateLivraison) < :date_id OR :date_id IS NULL) "
			       + "AND ( m.estHISM =:estHISM OR :estHISM IS NULL OR m.estHISM IS NULL) "
			       + "GROUP BY d.id")
	List<Map<Long, Long>> countByDepartementWithFilter(@Param("metier_id") List<Long> metier_id, @Param("entite_id") List<Long> entite_id, @Param("materielType_id") List<Long> materielType_id,
	                                                   @Param("departement_id") Long departement_id, @Param("date_id") Integer date_id,
	                                                   @Param("estHISM") Boolean estHISM);
*/
	/*return entityManager.createQuery(
			"select role.roleName, upper(role.roleName), " +
			"lower(role.roleName), " +
			"count(distinct tu.userId), sum(tu.weight), avg(tu.weight) , max(tu.weight), min(tu.weight) " +
			"from TestUser as tu " +
			"left join tu.role as role " +
			" where " +
			//" role.roleName like '%E%' and " +
			" tu.weight >= (select avg(tu.weight) from TestUser tu) and " +
			" tu.userId between -10 and 16" +
			" group by role.roleName " +
			" having count(distinct tu.userId) > 0" +
			" order by role.roleName desc, sum(tu.weight) desc")
			.getResultList();*/
}
