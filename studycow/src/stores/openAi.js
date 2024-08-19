import { create } from "zustand";
import axios from "axios";
import useInfoStore from "./infos";
import Notiflix from "notiflix";

const API_URL = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/studycow/";

const handleError = (error, errorMessage) => {
  console.error(errorMessage, error);
  Notiflix.Confirm.show(
      '오류 발생',
      `${errorMessage} 다시 시도하시겠습니까?`,
      '예',
      '아니오',
      () => {
        // 사용자가 '예'를 선택한 경우 - 여기서는 아무 작업도 하지 않습니다.
        // 재시도 로직은 컴포넌트에서 처리해야 합니다.
      },
      () => {
        // 사용자가 '아니오'를 선택한 경우
        Notiflix.Notify.info('작업이 취소되었습니다.');
      }
  );
  return null;
};

const useOpenAiStore = create((set, get) => ({
  getToken: () => useInfoStore.getState().token,

  generatePlanner: async (startDay, studyTime) => {
    const token = get().getToken();
    try {
      const response = await axios.get(`${API_URL}openai/auto-planner`, {
        headers: { Authorization: `Bearer ${token}` },
        params: { startDay, studyTime },
      });
      if (response.status === 200 && response.data) {
        return response.data;  // 응답 데이터가 존재하면 반환
      } else {
        throw new Error("Unexpected response structure");
      }
    } catch (error) {
      return handleError(error, "플래너를 생성하지 못했습니다.");
    }
  },

  registerPlans: async (plans) => {
    const token = get().getToken();
    try {
      for (let plan of plans) {
        const response = await axios.post(`${API_URL}planner/create`, plan, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });
        if (response.status !== 201) {
          throw new Error("Failed to register plan");
        }
      }
      return true;
    } catch (error) {
      handleError(error, "플래너를 등록하지 못했습니다.");
      return false;
    }
  },

  generateAdvice: async (adviceData) => {
    const token = get().getToken();
    try {
      const response = await axios.post(
          `${API_URL}openai/advice-score`,
          adviceData,
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
      );
      if (response.status === 200 && response.data) {
        return response.data;
      } else {
        throw new Error("Unexpected response structure");
      }
    } catch (error) {
      return handleError(error, "성적 조언을 생성하지 못했습니다.");
    }
  },
}));

export default useOpenAiStore;