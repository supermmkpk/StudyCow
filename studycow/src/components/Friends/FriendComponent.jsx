import FriendSearch from "./FriendSearch";
import FriendList from "./FriendList";
import FriendRequest from "./FriendRequest";
import "./styles/FriendComponent.css";

const FriendComponent = () => {
  return (
    <div className="Mainbox01">
      <FriendSearch />
      <div className="Mainbox02">
        <FriendList />
        <FriendRequest />
      </div>
    </div>
  );
};

export default FriendComponent;
