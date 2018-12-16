package com.example.demo.Repository;

import com.example.demo.Model.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepo extends CrudRepository<Schedule,Long> {
    Schedule findByScheduleid(Long id);
     Schedule deleteByScheduleid(Long id);
}
