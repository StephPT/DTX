package com.steph.dtx.database.dao;

import com.steph.dtx.database.entity.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, String> {
}
