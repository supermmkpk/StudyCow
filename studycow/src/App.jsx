import "./App.css";
import PageRoutes from "./routes/PageRoutes";
import { useEffect } from "react";

function App() {
  useEffect(() => {
    // 애플리케이션이 로드될 때 localStorage 지우기
    localStorage.clear(); // 모든 데이터를 지우거나, 필요에 따라 특정 항목만 지울 수 있음
  }, []);
  return (
    <>
      <PageRoutes />
    </>
  );
}

export default App;
