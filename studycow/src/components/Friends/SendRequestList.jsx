import "./styles/SendRequestList.css";
import Reacgt, { useEffect, useState } from "react";
import axios from "axios";
import useInfoStore from "../../stores/infos";

const SendRequestList = () => {
  const [sendRequests, setSendRequests] = useState([]);
  const { token } = useInfoStore();

  useEffect(() => {
    const fetchSendRequests = async () => {
      try {
        if (!token) {
          console.error("토큰이 없습니다.");
          return;
        }

        const response = await axios.get(
          "http://localhost:8080/studycow/friend/request/sent",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setSendRequests(response.data);
      } catch (error) {
        console.error("API 요청 실패:", error);
      }
    };

    fetchSendRequests();
  }, [token]);

  return (
    <div>
      <p className="FriendsTitle">보낸 친구 요청 목록</p>
      <div className="listBox02">
        {sendRequests.length > 0 ? (
          sendRequests.map((sendRequest) => (
            <div
              key={sendRequest.counterpartUserId}
              className="sendRequestItem"
            >
              <p>{sendRequest.counterpartUserNickname}</p>
            </div>
          ))
        ) : (
          <p>보낸 친구 요청 목록을 불러오는 중...</p>
        )}
      </div>
    </div>
  );
};

export default SendRequestList;
