import { create } from "zustand";
import axios from "axios";
import useInfoStore from "./infos";

const API_URL = `http://localhost:8080/studycow/`;

const useFriendsStore = create((set) => ({
  friends: [],
  getRequests: [],
  sendRequests: [],
  searchedNickname: "",
  searchedFriends: [],
  fetchFriends: async () => {
    const { token } = useInfoStore.getState();

    if (!token) {
      console.error("토큰이 없습니다.");
      return;
    }

    try {
      const response = await axios.get(API_URL + "friend/list", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const friendsData = response.data.map((friend) => ({
        ...friend,
        friendThumb: friend.friendThumb ?? "/src/assets/defaultProfile.png",
      }));

      set({ friends: friendsData });
    } catch (error) {
      console.error("API 요청 실패:", error);
    }
  },
  removeFriend: async (userId) => {
    const { token } = useInfoStore.getState();

    if (!token) {
      console.error("토큰이 없습니다.");
      return;
    }

    try {
      await axios.delete(API_URL + `friend/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      set((state) => ({
        friends: state.friends.filter(
          (friend) => friend.friendUserId !== userId
        ),
      }));
      alert("해당 친구를 삭제했소...");
    } catch (error) {
      alert("친구 삭제에 실패했소...");
    }
  },
  fetchGetRequests: async () => {
    const { token } = useInfoStore.getState();

    if (!token) {
      console.error("토큰이 없습니다.");
      return;
    }

    try {
      const response = await axios.get(API_URL + `friend/request/received`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const getRequestsData = response.data.map((getRequest) => ({
        ...getRequest,
        counterpartUserThumb:
          getRequest.counterpartUserThumb ?? "/src/assets/defaultProfile.png",
      }));

      set({ getRequests: getRequestsData });
    } catch (error) {
      console.error("API 요청 실패:", error);
    }
  },
  acceptGetRequest: async (friendRequestId) => {
    const { token } = useInfoStore.getState();

    if (!token) {
      console.error("토큰이 없습니다.");
      return;
    }

    try {
      // POST 요청을 보내는 axios 호출
      await axios.post(
        API_URL + `friend/accept/${friendRequestId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      set((state) => ({
        getRequests: state.getRequests.filter(
          (getRequest) => getRequest.id !== friendRequestId
        ),
      }));
      // 요청 성공 시 추가 작업 (예: 사용자에게 알림, 상태 업데이트 등)
      alert("친구 요청을 수락했소!");
      await useFriendsStore.getState().fetchFriends();
    } catch (error) {
      // 오류 발생 시 에러 메시지 표시
      console.error("친구 요청 수락 실패:", error);
      alert("친구 요청 수락에 실패했소...");
    }
  },
  fetchSendRequests: async () => {
    const { token } = useInfoStore.getState();

    if (!token) {
      console.error("토큰이 없습니다.");
      return;
    }

    try {
      const response = await axios.get(API_URL + `friend/request/sent`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const sendRequestsData = response.data.map((sendRequest) => ({
        ...sendRequest,
        counterpartUserThumb:
          sendRequest.counterpartUserThumb ?? "/src/assets/defaultProfile.png",
      }));

      set({ sendRequests: sendRequestsData });
    } catch (error) {
      console.error("API 요청 실패:", error);
    }
  },
  cancelSendRequest: async (requestId) => {
    const { token } = useInfoStore.getState();

    if (!token) {
      console.error("토큰이 없습니다.");
      return;
    }

    try {
      await axios.delete(API_URL + `friend/request/cancel/${requestId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      set((state) => ({
        sendRequests: state.sendRequests.filter(
          (sendRequest) => sendRequest.id !== requestId
        ),
      }));
      alert("친구 요청을 취소했소!");
    } catch (error) {
      console.error("친구 요청 취소 실패:", error);
      alert("친구 요청 취소에 실패했소...");
    }
  },
  setSearchedNickname: (searchedNickname) => set({ searchedNickname }),
  fetchSearchedFriends: async (searchedNickname) => {
    const { token } = useInfoStore.getState();

    if (!token) {
      console.error("토큰이 없습니다.");
      return;
    }

    try {
      const response = await axios.get(API_URL + `user/nickname`, {
        params: { nickname: searchedNickname },
      });
      set({ searchedfriends: response.data });
    } catch (error) {
      console.error("Error fetching friends:", error);
      set({ searchedFriends: [] });
    }
  },
}));

export default useFriendsStore;
