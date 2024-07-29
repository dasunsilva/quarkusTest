// import UserHome from "./UserHome";
import { Container } from "react-bootstrap";
import "./assets/css/App.css";
// import "bootstrap/dist/css/bootstrap.min.css";
import MainNavBar from "./components/navBar/MainNavBar";
import DataTable from "./components/DataTable";
import { useState } from "react";

function App() {
  const [page, setPage] = useState<string>("user");

  return (
    <main className="main">
      <MainNavBar setPageFn={setPage} />
      <Container id="testContainer">
        {page === "getUsers" && <UserDataTable>}
        {page === "getOneUser" && "GetOne"}
        {page === "addUser" && "Add User"}
        {page === "editUser" && "Edit user"}
        {page === "removeUser" && "Remove user"}
      </Container>
    </main>
  );
}

export default App;
