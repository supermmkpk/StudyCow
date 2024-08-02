import { create } from "zustand";
import axios from "axios";
import useInfoStore from "./infos";

const API_URL =
  import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/studycow/";

const useStudyStore = create((set, get) => ({
  studyRoomData: {},
  rooms: [],

  setStudyRoomData: (data) => set({ studyRoomData: data }),

  submitStudyRoomData: async () => {
    const { token } = useInfoStore.getState();

    try {
      const { studyRoomData } = get();
      const formData = new FormData();
      formData.append("roomTitle", studyRoomData.roomTitle);
      formData.append("roomMaxPerson", studyRoomData.roomMaxPerson);
      formData.append("roomEndDate", studyRoomData.roomEndDate);
      formData.append("roomContent", studyRoomData.roomContent);
      if (studyRoomData.roomThumb) {
        formData.append("roomThumb", studyRoomData.roomThumb);
      }

      await axios.post(API_URL + `room/create`, formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "multipart/form-data",
        },
      });
      return true;
    } catch (error) {
      console.error("에러가 발생했습니다:", error);
      return false;
    }
  },

  fetchRooms: async () => {
    const { token } = useInfoStore.getState();

    try {
      const response = await axios.get(API_URL + `room/list`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const rooms = response.data.map((room) => ({
        id: room.id,
        title: room.roomTitle,
        thumb: room.roomThumb,
        maxPerson: room.roomMaxPerson,
        nowPerson: room.roomNowPerson,
      }));
      set({ rooms });
    } catch (error) {
      console.error("방 정보를 가져오는 데 실패했습니다.", error);
    }
  },
}));

export default useStudyStore;
