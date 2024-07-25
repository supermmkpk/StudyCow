import {create} from 'zustand';

const useInfoStore = create((set) => ({
  // 임시 유저 정보
  isLogin: true,
  userInfo: {
    email: 'cowhead@studycow.com',
    name: '소머리국밥' ,
    grade: '1++',
    exp:'100,000,000',
    todayStudyTime: '03:00:00',
    onlineFriends: 3,
    friends: 4,
    ranks: 1,
  },


  // 임시 로그아웃 로직
  logout: (navigate) => {
    set({ isLogin: false });
    navigate('/login'); // 로그아웃 후 로그인 페이지로 리디렉트
  },

  // 임시 회원탈퇴 로직
  resign: (navigate) => {
    set({ isLogin: false });
    navigate('/login'); // 로그아웃 후 로그인 페이지로 리디렉트
  },

  // 임시 토글 동작 로직
  isOpen: false,
  toggleDropdown: () => set((state) => {
    const newState = !state.isOpen;
    return { isOpen: newState };
  })
}));

export default useInfoStore;