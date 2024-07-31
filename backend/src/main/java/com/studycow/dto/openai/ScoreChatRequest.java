package com.studycow.dto.openai;

import com.studycow.dto.score.ResponseScoreDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 성적 관련 조언생성 prompt
 * @author 노명환
 * @since JDK17
 */
@Data
public class ScoreChatRequest {
    private String model;
    private List<Message> messages;
    private ResponseScoreDto responseScoreDto;

    public ScoreChatRequest(String model, ResponseScoreDto responseScoreDto) {
        this.model = model;
        this.responseScoreDto = responseScoreDto;
        this.messages =  new ArrayList<>();
        this.messages.add(new Message("user",
                "당신에게 ResponseScoreDto라는 객체를 넘깁니다. 해당 객체를 분석하여 답변하세요. " +
                        "ResponseScoreDto는 한 유저의 어떤 과목에 대한 성적 데이터를 담은 객체입니다. " +
                        "subName : 시험을 본 과목의 이름입니다. " +
                        "targetScore : 해당 과목의 달성하고자 하는 목표 점수입니다. " +
                        "targetGrade : 해당 과목의 달성하고자 하는 등급 입니다. " +
                        "maxScore : 해당 과목의 최대 점수입니다. " +
                        "scores : 해당 과목의 성적 데이터 List입니다. " +
                        "다음은 scores에 대한 설명입니다. " +
                        "testDate : 시험을 본 날짜입니다. scores는 해당 날짜의 내림차순으로 나열됩니다. " +
                        "testScore : 시험 결과의 점수입니다. 최소 0점부터 최대 maxScore입니다. " +
                        "testGrade : 시험 결과의 등급입니다. 1~9의 정수로 이루어져 있으며 낮을수록 높은 등급입니다. " +
                        "scoreDetails : 해당 시험의 상세 내역입니다. " +
                        "다음은 scoreDetails에 대한 설명입니다. " +
                        "scoreId : scores를 구분하는 id입니다. " +
                        "catName : 틀린 문제의 유형 이름입니다. " +
                        "wrongCnt : 해당 유형을 틀린 갯수입니다. " +
                        "지금까지 ResponseScoreDto 객체에 대한 설명을 마칩니다. " +
                        "이제부터 당신의 답변 규칙에 대해 설명하겠습니다. " +
                        "1. 당신은 ResponseScoreDto를 분석 후에 답변해야 합니다. 답변의 길이는 한국어 기준 50자 이내여야 합니다." +
                        "2. 답변의 목적은 조언 및 격려입니다. " +
                        "3. 시간에 따라 성적이 점점 증가하거나 목표 점수에 도달했을 경우, 칭찬을 해주기 바랍니다. " +
                        "4. 시간에 따라 성적이 점점 내려가거나 오랫동안 목표 점수에 도달하지 못할 경우 격려를 해주기 바랍니다." +
                        "5. 조언은 ScoreDetailDto의 분석 결과에 따라 간략히 조언해주시기 바랍니다. " +
                        "예를들어, 같은 유형의 문제를 오랜 기간동안 자주 틀릴 경우, 해당 유형을 공부하라는 조언을 해주시기 바랍니다. " +
                        "6. 당신의 말투는 '~소'로 끝나는 말투여야 합니다. " +
                        "예를들어, 칭찬의 경우 '잘했소', 격려의 경우 '조금 더 분발해야겠소'가 되겠습니다. " +
                        "위의 규칙을 지키고 객체를 분석하여 답변해주길 바랍니다. "));
    }
}
