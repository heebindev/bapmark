package com.heebin.bapmark.service;

import com.heebin.bapmark.entity.StampBoard;
import com.heebin.bapmark.entity.User;
import com.heebin.bapmark.repository.StampBoardRepository;
import com.heebin.bapmark.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StampBoardService {

    private final StampBoardRepository stampBoardRepository;
    private final UserRepository userRepository;

    public StampBoard createStampBoard(String email, String title, String description, String backgroundColor) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        StampBoard stampBoard = new StampBoard();
        stampBoard.setUser(user);
        stampBoard.setTitle(title);
        stampBoard.setDescription(description);
        stampBoard.setBackgroundColor(backgroundColor);

        return stampBoardRepository.save(stampBoard);
    }
}

