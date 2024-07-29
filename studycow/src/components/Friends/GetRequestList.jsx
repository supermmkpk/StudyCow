import "./styles/GetRequestList.css";
import Reacgt, { useEffect, useState } from "react";
import axios from "axios";
import useInfoStore from "../../stores/infos";

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
        setGetRequests(response.data);
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
            <div key={getRequest.counterpartUserId} className="getRequestItem">
              <p>{getRequest.counterpartUserId}번 친구</p>
            </div>
          ))
        ) : (
          <p>보낸 친구 요청 목록을 불러오는 중...</p>
        )}
      </div>
    </div>
  );
};

export default GetRequestList;
