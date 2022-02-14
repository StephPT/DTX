package com.steph.dtx.database.dao;

import com.steph.dtx.database.entity.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HolidaysRepository extends JpaRepository<Holidays, String> {

    @Query("SELECT u FROM Holidays u WHERE u.date between ?1 AND ?2")
    List<Holidays> findHolidaysByDateBetween(Date firstDate, Date secondDate);
}
