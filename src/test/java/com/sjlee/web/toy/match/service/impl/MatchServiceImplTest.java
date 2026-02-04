package com.sjlee.web.toy.match.service.impl;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.domain.MatchStatus;
import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.match.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @InjectMocks
    private MatchServiceImpl matchService;

    @Mock
    private MatchRepository matchRepository;

    @Test
    void getMatchList() {
        // given
        List<MatchList> mockList = List.of(
                new MatchList(
                        1L,
                        LocalDateTime.now(),
                        "상대팀",
                        2,
                        1,
                        MatchStatus.DONE,
                        10L
                )
        );

        when(matchRepository.findMatchList())
                .thenReturn(mockList);

        // when
        List<MatchList> result = matchService.getMatchList();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatus()).isEqualTo(MatchStatus.DONE);

        verify(matchRepository).findMatchList();
    }

    @Test
    void createMatch() {
        // given
        Match match = mock(Match.class);

        when(match.getId()).thenReturn(100L);
        when(matchRepository.save(match)).thenReturn(match);

        // when
        Long result = matchService.createMatch(match);

        // then
        assertThat(result).isEqualTo(100L);
        verify(matchRepository).save(match);
    }
}