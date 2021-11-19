package com.example.petto.repository.memberRelated;

import com.example.petto.entity.MemberRelated.UpdateAlarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UpdateAlarmRepository extends JpaRepository<UpdateAlarm, Long> {

    //@Query("select ua from UpdateAlarm ua where ua.id = :id")
    List<UpdateAlarm> findById(String id);

    @Transactional
    @Modifying
    void deleteByAlarmNo(Long alarmNo);

    @Transactional
    @Modifying
    void deleteById(String id);
}
