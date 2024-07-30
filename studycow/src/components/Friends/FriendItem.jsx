import "./styles/FriendItem.css";
import axios from "axios";

const FriendItem = ({ thumbnail, nickname, userId, onDelete, token }) => {
  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/studycow/friend/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      if (onDelete) {
        onDelete(userId);
      }
    } catch (error) {
      console.error("친구 삭제 실패:", error);
    }
  };

  return (
    <div className="friendItem">
      <div className="friendInfo">
        <img
          src={thumbnail}
          alt={`${nickname}의 썸네일`}
          className="friendThumbnail"
        />
        <p className="friendNickname">{nickname}</p>
      </div>
      <div>
        <button className="friendDelete" onClick={handleDelete}>
          💔
        </button>
      </div>
    </div>
  );
};

export default FriendItem;
