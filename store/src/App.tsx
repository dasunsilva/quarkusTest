import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./assets/css/App.css";
import MainNavBar from "./components/navBar/MainNavBar";
import UserDataTable from "./components/user/UserDataTable";
import AddNewUser from "./components/user/UserOperations";
import KeycloakInstance from "./services/KeycloakInstance";
import KeyclockProvider from "./services/KeycloakProvider";

function App() {
  return (
    <main className="main">
      <Router>
        <KeyclockProvider>
          <MainNavBar />
          <KeycloakInstance />
          <Routes>
            <Route path="/users">
              <Route index path="/users/get" element={<UserDataTable />} />
              <Route
                path="/users/add"
                element={<AddNewUser edit={false} remove={false} />}
              />
              <Route
                path="/users/edit"
                element={<AddNewUser edit={true} remove={false} />}
              />
              <Route
                path="/users/remove"
                element={<AddNewUser edit={false} remove={true} />}
              />
            </Route>
          </Routes>
        </KeyclockProvider>
      </Router>
    </main>
  );
}

export default App;
