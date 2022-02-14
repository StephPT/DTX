package com.steph.dtx.web.service;

import static com.steph.dtx.utility.DateUtility.*;

import com.steph.dtx.database.dao.TimesheetRepository;
import com.steph.dtx.database.entity.bank.Location;
import com.steph.dtx.database.entity.bank.BankHolidayDAO;
import com.steph.dtx.database.entity.bank.BankHolidays;
import com.steph.dtx.database.entity.Timesheet;
import com.steph.dtx.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.steph.dtx.utility.DateUtility.convertToDate;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private EntityManager entityManager;

    private BankHolidays bankHolidays;


    @PostConstruct
    public void init() {
        final String url = "https://gov.uk/bank-holidays.json";
        RestTemplate restTemplate = new RestTemplate();
        bankHolidays = restTemplate.getForObject(url, BankHolidays.class);
    }

    public double getTimeForMonth(User user) {
        return getTimeBetween(convertToDate(getFirstDayOfMonth()), convertToDate(getLastDayOfMonth()), user);
    }

    public List<Timesheet> getTimesheetBetween(Date startDate, Date endDate, User user) {
        return entityManager
                .createQuery("SELECT t FROM Timesheet t WHERE t.user = :user AND t.date BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate, TemporalType.DATE)
                .setParameter("endDate", endDate, TemporalType.DATE)
                .setParameter("user", user)
                .getResultList();
    }

    private double getTimeBetween(Date startDate, Date endDate, User user) {
        Double result = (double) entityManager
                .createQuery("SELECT SUM(t.length) FROM Timesheet t WHERE t.user = :user AND t.date BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate, TemporalType.DATE)
                .setParameter("endDate", endDate, TemporalType.DATE)
                .setParameter("user", user)
                .getSingleResult();
        return result == null ? 0 : result;
    }

    public BankHolidayDAO getBankHolidays(Location location) {
        BankHolidayDAO result = null;
        switch (location) {
            case ENGLAND_AND_WALES:
                result = bankHolidays.getEnglandAndWales();
                break;
            case SCOTLAND:
                result = bankHolidays.getScotland();
                break;
            case IRELAND:
                result = bankHolidays.getNorthernIreland();
                break;
        }
        return result;
    }

    public double calculateWorkingHours() {
        LocalDate startDate = getFirstDayOfMonth();
        LocalDate endDate = getLastDayOfMonth();

        final Predicate<LocalDate> isBankHoldiday = localDate -> getBankHolidays(Location.ENGLAND_AND_WALES).getEvents().stream().anyMatch(e -> e.getDate().equals(localDate.toString()));
        final Predicate<LocalDate> isWeekend = localDate -> localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY;

        return (double) Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(endDate.getDayOfMonth())
                .filter(isWeekend.or(isBankHoldiday).negate()).count() * 7.5;
    }
}
