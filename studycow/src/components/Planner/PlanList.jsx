import React, { useState } from 'react';

const PlanList = ({ plans }) => {
  const [updatedPlans, setUpdatedPlans] = useState(plans);

  const sub_code_dic = {
    '1': '국어',
    '2': '수학',
    '3': '영어',
    '4': '한국사',
    '5': '사회탐구',
    '6': '과학탐구',
    '7': '직업탐구',
    '8': '제2외국어/한문'
  }


  // 계획 상태 업데이트 함수
  const handleCheckboxChange = (planId) => {
    setUpdatedPlans(prevPlans =>
      prevPlans.map(plan =>
        plan.planId === planId
          ? { ...plan, planStatus: plan.planStatus === 0 ? 1 : 0 }
          : plan
      )
    );
  };



  return (
    <div>
      {updatedPlans.map(plan => (
        <div key={plan.planId} style={{ marginBottom: '10px' }}>
          {!plan.planStatus && (
            <>
              <label>
                <input
                  type="checkbox"
                  checked={plan.planStatus === 1}
                  onChange={() => handleCheckboxChange(plan.planId)}
                />
                {`${sub_code_dic[`${plan.subCode}`]}`} {/* 과목 표시 */}
              </label>
              <span style={{ marginLeft: '10px' }}>
                {`${plan.planStudyTime}시간`} {/* 입력된 시간 표시 */}
              </span>
            </>
          )}
        </div>
      ))}
    </div>
  );
};

export default PlanList;
