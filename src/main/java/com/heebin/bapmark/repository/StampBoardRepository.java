package com.heebin.bapmark.repository;

import com.heebin.bapmark.entity.StampBoard;
import com.heebin.bapmark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StampBoardRepository extends JpaRepository<StampBoard, Long> {
    List<StampBoard> findByUser(User user);
}

