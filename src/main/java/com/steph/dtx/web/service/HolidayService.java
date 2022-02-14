package com.steph.dtx.web.service;

import static com.steph.dtx.utility.DateUtility.*;

import com.steph.dtx.database.dao.HolidaysRepository;
import com.steph.dtx.database.entity.Holidays;
import com.steph.dtx.database.entity.User;
import com.steph.dtx.utility.DateUtility;
import com.steph.dtx.web.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class HolidayService {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    public List<Holidays> getUpcomingHolidays() {
        return getHolidaysBetween(convertToDate(LocalDateTime.now()), convertToDate(LocalDateTime.now().plusWeeks(1).toLocalDate()));
    }

    public List<Holidays> getHolidaysBetween(Date startDate, Date endDate) {
        return entityManager
                .createQuery("SELECT h FROM Holidays h WHERE h.user = :user AND h.date BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate, TemporalType.DATE)
                .setParameter("endDate", endDate, TemporalType.DATE)
                .setParameter("user", userService.getUser())
                .getResultList();
    }

}
