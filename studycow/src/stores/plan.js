// planStore.js
import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import axios from 'axios';
import useInfoStore from './infos';

const API_URL = 'http://localhost:8080/studycow/';

const usePlanStore = create(
  persist(
    (set) => ({
      date: '2024-07-02',
      plans: [],
      saveDate: (day) => set({ date: day }),
      getDatePlanRequest: async (date) => {
        const { token } = useInfoStore.getState();
        const headers = {
          Authorization: `Bearer ${token}`,
        };
        try {
          const response = await axios.get(API_URL + 'planner/list/day', {
            params: { date },
            headers,
          });
          if (response.status === 200) {
            set({ plans: response.data ?? [] });
            return true;
          } else {
            throw new Error('정보불러오기 에러');
          }
        } catch (e) {
          console.log(e);
          return false;
        }
      },
    }),
    {
      name: 'plan-storage',
      getStorage: () => localStorage, // persist 스토리지 설정
    }
  )
);

export default usePlanStore;
