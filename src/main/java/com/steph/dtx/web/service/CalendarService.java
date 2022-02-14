package com.steph.dtx.web.service;

import com.steph.dtx.database.dao.UserRepository;
import com.steph.dtx.database.entity.bank.Location;
import com.steph.dtx.utility.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CalendarService {

    public static final String USER_NAME = "stomkins";

    public static final String UUID = "a82c5d96-0dae-4e25-9da4-cd8f6ed44c44";

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private TimesheetService timesheetService;

    @Autowired
    private UserRepository userRepository;

    public String holidays() {
        StringBuilder stringBuilder = new StringBuilder();
        String format = "{ title: 'Holiday', start: '%s', end: '%s' },";
        holidayService.getUpcomingHolidays(userRepository.findUserByUsername("stomkins")).forEach(holidays -> {
            stringBuilder.append(String.format(format, DateUtility.DATE_FORMAT.format(holidays.getDate()), DateUtility.DATE_FORMAT.format(holidays.getDate())));
        });
        return stringBuilder.toString();
    }

    public String bankHolidays() {
        StringBuilder stringBuilder = new StringBuilder();
        String format = "{ title: '%s', start: '%s', end: '%s' },";
        timesheetService.getBankHolidays(Location.ENGLAND_AND_WALES).getEvents().forEach(holiday -> {
            stringBuilder.append(String.format(format, holiday.getTitle(), DateUtility.DATE_FORMAT.format(Date.valueOf(holiday.getDate())), DateUtility.DATE_FORMAT.format(Date.valueOf(holiday.getDate()))));
        });
        return stringBuilder.substring(0, stringBuilder.toString().length() - 1);
    }

}
