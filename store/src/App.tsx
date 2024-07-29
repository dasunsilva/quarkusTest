// import UserHome from "./UserHome";
import { Container } from "react-bootstrap";
import "./assets/css/App.css";
// import "bootstrap/dist/css/bootstrap.min.css";
import MainNavBar from "./components/navBar/MainNavBar";
import { useState } from "react";
import UserDataTable from "./components/UserDataTable";
import ItemDataTable from "./components/ItemDataTable";
import BillDataTable from "./components/BillDataTable";
import { CurrentPageData } from "./types/CurrentPageData";

function App() {
  const [page, setPage] = useState<CurrentPageData>({
    main: "user",
    sub: "getAllUser",
  });

  return (
    <main className="main">
      <MainNavBar setPageFn={setPage} />
      <Container id="testContainer">
        {page.main === "user" && (
          <>
            {page.sub === "getAllUser" && <UserDataTable />}
            {page.sub === "getOneUser" && "GetOneUser"}
            {page.sub === "addUser" && "Add User"}
            {page.sub === "editUser" && "Edit user"}
            {page.sub === "removeUser" && "Remove user"}
          </>
        )}

        {page.main === "item" && (
          <>
            {page.sub === "getAllItem" && <ItemDataTable />}
            {page.sub === "getOneItem" && "GetOneItem"}
            {page.sub === "addItem" && "Add Item"}
            {page.sub === "editItem" && "Edit Item"}
            {page.sub === "removeItem" && "Remove Item"}
          </>
        )}

        {page.main === "bill" && (
          <>
            {page.sub === "getAllBill" && <BillDataTable />}
            {page.sub === "getOneBill" && "GetOneBill"}
            {page.sub === "addBill" && "Add Bill"}
            {page.sub === "editBill" && "Edit Bill"}
            {page.sub === "removeBill" && "Remove Bill"}
          </>
        )}
      </Container>
    </main>
  );
}

export default App;
