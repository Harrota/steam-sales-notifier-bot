package com.harrota.dao;

import com.harrota.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDao extends JpaRepository<App, Long> {
}
