package com.sjlee.web.toy.ground.service.impl;

import com.sjlee.web.toy.ground.dto.GroundSelect;
import com.sjlee.web.toy.ground.repository.GroundRepository;
import com.sjlee.web.toy.ground.service.GroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroundServiceImpl implements GroundService {
    private final GroundRepository groundRepository;

    /**
     * 경기 등록 화면 - 구장 선택 목록
     */
    public List<GroundSelect> getGroundSelectItems() {
        return groundRepository.findSelectItems();
    }
}
