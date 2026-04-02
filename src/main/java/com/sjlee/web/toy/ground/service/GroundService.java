package com.sjlee.web.toy.ground.service;

import com.sjlee.web.toy.ground.domain.Ground;
import com.sjlee.web.toy.ground.dto.GroundList;
import com.sjlee.web.toy.ground.dto.GroundSelect;
import com.sjlee.web.toy.ground.repository.GroundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroundService {
    private final GroundRepository groundRepository;

    /**
     * 구장 목록 조회
     */
    public Page<GroundList> getGroundList(Pageable pageable) {
        return groundRepository.findGroundList(pageable);
    }

    /**
     * 경기 등록 화면 - 구장 선택 목록
     */
    public List<GroundSelect> getGroundSelectItems() {
        return groundRepository.findSelectItems();
    }

    /**
     * 구장 등록
     */
    public Long createGround(Ground ground) {
        return groundRepository.save(ground).getId();
    }
}
