import GetRequestList from "./GetRequestList";
import SendRequestList from "./SendRequestList";
import "./styles/FriendRequest.css";

const FriendRequest = () => {
  return (
    <div className="friendRequestBox">
      <GetRequestList />
      <SendRequestList />
    </div>
  );
};

export default FriendRequest;
