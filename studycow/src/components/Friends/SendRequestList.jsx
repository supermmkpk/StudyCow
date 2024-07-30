import "./styles/SendRequestList.css";
import Reacgt, { useEffect, useState } from "react";
import axios from "axios";
import useInfoStore from "../../stores/infos";
import SendRequestItem from "./SendRequestItem";

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

        const sendRequestsData = response.data.map((sendRequest) => ({
          ...sendRequest,
          counterpartUserThumb:
            sendRequest.counterpartUserThumb ??
            "/src/assets/defaultProfile.png",
        }));

        setSendRequests(sendRequestsData);
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
            <SendRequestItem
              key={sendRequest.id}
              requestId={sendRequest.id}
              userId={sendRequest.counterpartUserId}
              nickname={sendRequest.counterpartUserNickname}
              thumbnail={sendRequest.counterpartUserThumb}
            />
          ))
        ) : (
          <p>보낸 친구 요청이 없소</p>
        )}
      </div>
    </div>
  );
};

export default SendRequestList;
