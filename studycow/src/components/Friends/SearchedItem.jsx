import "./styles/SearchedItem.css";

const SearchedItem = ({ friend }) => {
  return <div className="friendItem">{friend.userNickName}</div>;
};

export default SearchedItem;
