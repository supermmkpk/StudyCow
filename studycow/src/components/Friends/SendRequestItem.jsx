import "./styles/SendRequestItem.css";
import useFriendsStore from "../../stores/friends";

const SendRequestItem = ({ requestId, nickname, thumbnail }) => {
  const cancelSendRequest = useFriendsStore((state) => state.cancelSendRequest);

  const handleSendCancel = () => {
    cancelSendRequest(requestId);
  };

  return (
    <div className="sendRequestItem">
      <div className="sendRequestInfo">
        <img
          src={thumbnail}
          alt={`${nickname}의 썸네일`}
          className="sendRequestThumbnail"
        />
        <p className="sendRequestNickname">{nickname}</p>
      </div>
      <div>
        <button className="sendCancel" onClick={handleSendCancel}>
          ➖
        </button>
      </div>
    </div>
  );
};

export default SendRequestItem;
