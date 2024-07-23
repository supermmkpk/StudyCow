import {create} from 'zustand';

const useInfoStore = create((set) => ({
  isLogin: true,
  userInfo: {
    name: '소머리국밥' ,
    grade: '1++',
    exp:'100,000,000',
    todayStudyTime: '03:00:00',
    onlineFriends: 3,
    friends: 4,
    ranks: 1,
  },
  isOpen: false,
  toggleDropdown: () => set((state) => {
    const newState = !state.isOpen;
    console.log(newState); // 상태 변경 시 콘솔에 로그 출력
    return { isOpen: newState };
  })
}));

export default useInfoStore;