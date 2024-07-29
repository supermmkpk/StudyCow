import { create } from "zustand";
import { persist } from "zustand/middleware";
import axios from "axios";

const API_URL = "http://localhost:8080/studycow/";

const useInfoStore = create(
  persist(
    (set) => ({
      // 유저 정보
      isLogin: false, // 로그인 여부
      token: null, // 토큰 저장
      userInfo: {
        userEmail: null,
        userPublic: 0,
        userThumb: "/src/assets/defaultProfile.png",
        userGrade: {
          gradeCode: 0,
          gradeName: "브론즈",
          minExp: 0,
          maxExp: 0,
        },
        userExp: 0,
        userNickName: null,
      },

      // 임시 회원가입 로직
      sendRegisterRequest: async (userEmail, userPassword, userNickName) => {
        const data = {
          userEmail,
          userPassword,
          userNickName,
        };
        try {
          const response = await axios.post(
            API_URL + "api/v1/auth/register",
            data
          );
          if (response.status === 201) {
            return true;
          } else {
            throw new Error("회원가입 에러");
          }
        } catch (e) {
          console.log(e);
          return false;
        }
      },

      // 임시 로그인 로직
      sendLoginRequest: async (userEmail, password) => {
        const data = { userEmail, password };
        try {
          const response = await axios.post(
            API_URL + "api/v1/auth/login",
            data
          );
          console.log(response.data);
          if (response.status === 200) {
            set({
              token: response.data.token ?? null,
              isLogin: true,
              userInfo: {
                // 여기에서 response 데이터에 따라 userInfo를 업데이트 합니다.
                userEmail: response.data.userEmail ?? null,
                userNickName: response.data.userNickName ?? null,
                userThumb:
                  response.data?.userThumb ?? "/src/assets/defaultProfile.png",
                userGrade: {
                  gradeCode: response.data.userGrade.gradeCode ?? 0,
                  gradeName: response.data.userGrade.gradeName ?? "브론즈",
                  minExp: response.data.userGrade.minExp ?? 0,
                  maxExp: response.data.userGrade.maxExp ?? 0,
                },
                userExp: response.data.userExp ?? 0,
              },
            });
            console.log(response.data.userEmail);
            console.log(response.data.userNickName);
            console.log(response.data.userExp);
            console.log(response.data.userGrade.gradeCode);
            console.log(response.data.userGrade.gradeName);
            console.log(response.data.userGrade.minExp);
            console.log(response.data.userGrade.maxExp);
            return true;
          } else {
            throw new Error("로그인에러");
          }
        } catch (e) {
          console.log(e);
          return false;
        }
      },

      // 임시 로그아웃 로직
      logout: (navigate) => {
        set({ isLogin: false, token: null });
        navigate("/login"); // 로그아웃 후 로그인 페이지로 리디렉트
      },

      // 임시 회원탈퇴 로직
      resign: (navigate) => {
        set({ isLogin: false });
        navigate("/login"); // 로그아웃 후 로그인 페이지로 리디렉트
      },

      // 임시 토글 동작 로직
      isOpen: false,
      toggleDropdown: () =>
        set((state) => {
          const newState = !state.isOpen;
          return { isOpen: newState };
        }),
    }),
    {
      name: "info-storage",
    }
  )
);

export default useInfoStore;
