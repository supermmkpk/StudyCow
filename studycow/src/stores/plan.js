// planStore.js
import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import axios from 'axios';
import useInfoStore from './infos';

const API_URL = 'https://localhost:8443/studycow/';

const usePlanStore = create(
  persist(
    (set) => ({
      date: '2024-07-02',
      plans: [],
      updatePlanStatus: (planId) => {
        set((state) => ({
          plans: state.plans.map(plan =>
            plan.planId === planId
              ? { ...plan, planStatus: plan.planStatus === 0 ? 1 : 0 }
              : plan
          )
        }))},
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
    }
  )
);

export default usePlanStore;
