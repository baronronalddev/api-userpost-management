package com.bancom.ms.domain.repositories;

import com.bancom.ms.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    @Query("select case when count(c)> 0 then true else false end from UserEntity c where lower(c.name) like lower(:name)")
    boolean existsName(@Param("name") String name);

    @Query("select case when count(c)> 0 then true else false end from UserEntity c where lower(c.lastName) like lower(:lastName)")
    boolean existsLastName(@Param("lastName") String lastName);

}
