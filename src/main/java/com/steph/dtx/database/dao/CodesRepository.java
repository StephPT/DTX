package com.steph.dtx.database.dao;

import com.steph.dtx.database.entity.Codes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodesRepository extends JpaRepository<Codes, String> {
    Codes findByCode(int code);
}
