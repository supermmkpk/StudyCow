import "./styles/GetRequestItem.css";

const GetRequestItem = ({ requestId, userId, nickname, thumbnail }) => {
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
        <p className="btnposition01">버튼위치</p>
      </div>
    </div>
  );
};

export default GetRequestItem;
