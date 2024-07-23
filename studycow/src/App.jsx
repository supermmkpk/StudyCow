import "./App.css";
import Main_Unlogin from "./views/Main_Unlogin";
import ErrorPage from "./components/ErrorPage";
import LoadingPage from "./components/LoadingPage";
import Header from "./components/navbar/navBar";

function App() {
  // return <Main_Unlogin />;
  return (
    <>
      <Header />
      <main>
        <Main_Unlogin />
      </main>
      {/* <ErrorPage />
      <LoadingPage /> */}
    </>
  );
}

export default App;
