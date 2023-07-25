package org.programming.dao;

import org.programming.bean.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDao extends JpaRepository <Profile, String>{
    Profile findByType(String type);
}
