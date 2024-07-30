import "./styles/FriendList.css";
import React, { useEffect, useState } from "react";
import axios from "axios";
import useInfoStore from "../../stores/infos";
import FriendItem from "./FriendItem";

const FriendList = () => {
  const [friends, setFriends] = useState([]);

  const { token } = useInfoStore(); // zustand를 통해 토큰 가져오기

  const handleDelete = (userId) => {
    setFriends(friends.filter((friend) => friend.friendUserId !== userId));
  };

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

        const friendsData = response.data.map((friend) => ({
          ...friend,
          friendThumb: friend.friendThumb ?? "/src/assets/defaultProfile.png",
        }));

        setFriends(friendsData);
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
            <FriendItem
              key={friend.friendUserId}
              userId={friend.friendUserId}
              nickname={friend.friendNickname}
              thumbnail={friend.friendThumb}
              onDelete={handleDelete}
              token={token}
            />
          ))
        ) : (
          <p>아직 추가한 친구가 없소</p>
        )}
      </div>
    </div>
  );
};

export default FriendList;
