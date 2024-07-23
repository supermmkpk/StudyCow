import "./styles/Infobox.css";

const Infobox = ({ name }) => {
  return (
    <div className="whole">
      <div className="title">
        <p>{name}</p>
      </div>
      <input className="content" type="text" />
    </div>
  );
};

export default Infobox;
