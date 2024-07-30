import "./styles/GetRequestItem.css";
import useFriendsStore from "../../stores/friends";

const GetRequestItem = ({ requestId, nickname, thumbnail }) => {
  const acceptGetRequest = useFriendsStore((state) => state.acceptGetRequest);

  const handleGetAccept = () => {
    acceptGetRequest(requestId);
  };

  return (
    <div className="getRequestItem">
      <div className="getRequestInfo">
        <img
          src={thumbnail}
          alt={`${nickname}의 썸네일`}
          className="getRequestThumbnail"
        />
        <p className="getRequestNickname">{nickname}</p>
      </div>
      <div>
        <button className="getAccept" onClick={handleGetAccept}>
          ➕
        </button>
      </div>
    </div>
  );
};

export default GetRequestItem;
