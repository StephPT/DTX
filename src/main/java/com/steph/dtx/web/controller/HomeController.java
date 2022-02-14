package com.steph.dtx.web.controller;

import com.steph.dtx.database.dao.UserRepository;
import com.steph.dtx.database.entity.User;
import com.steph.dtx.web.service.HolidayService;
import com.steph.dtx.web.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ViewScoped;

@Controller
@ViewScoped
public class HomeController {

    @Autowired
    private TimesheetService timesheetService;

    public String outstandingMessage() {
        String result = "";
        double month = timesheetService.getTimeForMonth();
        double workingHours = timesheetService.calculateWorkingHours();
        if(month < workingHours) {
            result = "You have missing hours this month.";
        } else if (month > workingHours) {
            result = "You've booked too many hours this month.";
        }
        return result;
    }

    public boolean isOutstandingTime() {
        return (timesheetService.getTimeForMonth() != timesheetService.calculateWorkingHours());
    }

    public double getTimeForMonth() {
        return timesheetService.getTimeForMonth();
    }

    public double getWorkingHours() {
        return timesheetService.calculateWorkingHours();
    }

}
