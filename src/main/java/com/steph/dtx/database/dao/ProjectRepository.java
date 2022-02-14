package com.steph.dtx.database.dao;

import com.steph.dtx.database.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
