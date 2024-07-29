import "./styles/FriendList.css";
import React, { useEffect, useState } from "react";
import axios from "axios";
import useInfoStore from "../../stores/infos";

const FriendList = () => {
  const [friends, setFriends] = useState([]);
  const { token } = useInfoStore(); // zustand를 통해 토큰 가져오기

  useEffect(() => {
    // API 요청을 보내는 함수
    const fetchFriends = async () => {
      try {
        if (!token) {
          console.error("토큰이 없습니다.");
          return;
        }

        const response = await axios.get(
          "http://localhost:8080/studycow/friend/list",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setFriends(response.data);
      } catch (error) {
        console.error("API 요청 실패:", error);
      }
    };

    // 컴포넌트가 마운트될 때 API 호출
    fetchFriends();
  }, [token]);

  return (
    <div>
      <p className="FriendsTitle">친구 목록</p>
      <div className="friendListBox">
        {friends.length > 0 ? (
          friends.map((friend) => (
            <div key={friend.friendUserId} className="friendItem">
              <p>{friend.friendEmail}</p>
            </div>
          ))
        ) : (
          <p>친구 목록을 불러오는 중...</p>
        )}
      </div>
    </div>
  );
};

export default FriendList;
