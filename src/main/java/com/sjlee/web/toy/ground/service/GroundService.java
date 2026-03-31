package com.sjlee.web.toy.ground.service;

import com.sjlee.web.toy.ground.dto.GroundList;
import com.sjlee.web.toy.ground.dto.GroundSelect;
import com.sjlee.web.toy.ground.repository.GroundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroundService {
    private final GroundRepository groundRepository;

    /**
     * 경기 등록 화면 - 구장 선택 목록
     */
    public List<GroundSelect> getGroundSelectItems() {
        return groundRepository.findSelectItems();
    }

    public List<GroundList> getGroundList() { return groundRepository.findGroundList();
    }
}
