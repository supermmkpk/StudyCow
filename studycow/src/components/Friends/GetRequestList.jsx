import "./styles/GetRequestList.css";
import Reacgt, { useEffect, useState } from "react";
import axios from "axios";
import useInfoStore from "../../stores/infos";
import GetRequestItem from "./GetRequestItem";

const GetRequestList = () => {
  const [getRequests, setGetRequests] = useState([]);
  const { token } = useInfoStore();

  useEffect(() => {
    const fetchGetRequests = async () => {
      try {
        if (!token) {
          console.error("토큰이 없습니다.");
          return;
        }

        const response = await axios.get(
          "http://localhost:8080/studycow/friend/request/received",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        const getRequestsData = response.data.map((getRequest) => ({
          ...getRequest,
          counterpartUserThumb:
            getRequest.counterpartUserThumb ?? "/src/assets/defaultProfile.png",
        }));

        setGetRequests(getRequestsData);
      } catch (error) {
        console.error("API 요청 실패:", error);
      }
    };

    fetchGetRequests();
  }, [token]);

  return (
    <div>
      <p className="FriendsTitle">받은 친구 요청 목록</p>
      <div className="listBox01">
        {getRequests.length > 0 ? (
          getRequests.map((getRequest) => (
            <GetRequestItem
              key={getRequest.id}
              requestId={getRequest.id}
              userId={getRequest.counterpartUserId}
              nickname={getRequest.counterpartUserNickname}
              thumbnail={getRequest.counterpartUserThumb}
            />
          ))
        ) : (
          <p>받은 친구 요청이 없소</p>
        )}
      </div>
    </div>
  );
};

export default GetRequestList;
