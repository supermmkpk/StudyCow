import "./styles/SendRequestItem.css";

const SendRequestItem = ({ requestId, userId, nickname, thumbnail }) => {
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
        <p className="btnposition02">버튼위치</p>
      </div>
    </div>
  );
};

export default SendRequestItem;
