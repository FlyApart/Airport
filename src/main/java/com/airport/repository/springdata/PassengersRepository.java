package com.airport.repository.springdata;

import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

/*@CacheConfig(cacheNames = "passengerInfo")*/
public interface PassengersRepository extends CrudRepository<Passengers, Long>, JpaRepository<Passengers, Long> {

	@Query("select p.passports from Passengers p  where p.id = :id")
	Set<Passports> findPassportsByPassportsId (Long id);//TODO rebase this in passportRepository

	/*@Cacheable*/
	@Query("from Passengers p where p.login= :login")
	Optional<Passengers> findByLogin (String login);

	// public Passengers findByLogin(String login);
	/*List<Passengers> findByNameAndSecondName (String name, String secondName);
	 *//*List<Passengers> findByUserNameAndUserSurnameAndLogin (String userName, String userSurname, String login);
    List<Passengers> findByUserNameAndUserSurnameAndLoginLike (String userName, String userSurname, String login);
    List<Passengers> findDistinctByUserNameLikeOrderByBirthDateAscUserIdDesc (String userName);*//*

    @Query(value = "select u from Passengers u", countQuery = "select count(u) from Passengers u")
    List<Passengers> qweqweqweqw ();*/

   /* @Query(value = "select u.* from m_users u", nativeQuery = true)
    List<Passengers> nativeQuery ();

    @Query("select u from Passengers u where u.userId > :lowerId")
    List<Passengers> qweqweqweqw1 (String lowerId);

    @Query("select u.userId, r.roleId from Passengers u join HibernateRole r on u.userId = r.user.userId where u.userId > :lowerId")
    List<Object[]> qweqweqweqw2 (@Param("lowerId") String qweqwe);

    @Query("select u.birthDate from Passengers u where u.login like %:lowerId%")
    List<Timestamp> qweqweqweqw3 (@Param("lowerId") String qweqwe);

    @Query("select u.userId, u.birthDate from Passengers u where u.userId > :lowerId")
    List<Object[]> qweqweqweqw4 (@Param("lowerId") String qweqwe);

    @Query("select u.userId, u.birthDate, r.roleName from Passengers u, HibernateRole r where u.userId > :lowerId")
    List<Object[]> qweqweqweqw5 (@Param("lowerId") String qweqwe);

    @Modifying(flushAutomatically = true)
    @Query("update Passengers u set u.birthDate = :birthDate")
    int updateUserBirthdayDate (@Param("birthDate") Timestamp birthDate);*/
}