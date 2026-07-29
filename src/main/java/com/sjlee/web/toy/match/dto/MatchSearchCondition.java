package com.sjlee.web.toy.match.dto;

import com.sjlee.web.toy.match.domain.MatchResult;
import com.sjlee.web.toy.match.domain.MatchType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchSearchCondition {
    private String matchMonth = "";
    private String opponentName = "";
    private MatchResult result;
    private MatchType matchType;
    private String groundName = "";
}
