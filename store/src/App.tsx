// import UserHome from "./UserHome";
import { Container } from "react-bootstrap";
import "./assets/css/App.css";
// import "bootstrap/dist/css/bootstrap.min.css";
import MainNavBar from "./components/navBar/MainNavBar";
import { useState } from "react";
import UserDataTable from "./components/user/UserDataTable";
import { CurrentPageData } from "./types/CurrentPageData";
import AddNewUser from "./components/user/UserOperations";

function App() {
  const [page, setPage] = useState<CurrentPageData>({
    main: "User",
    sub: "getAllUser",
  });

  return (
    <main className="main">
      <MainNavBar setPageFn={setPage} />
      <Container id="UIContainer">
        {page.main === "User" && (
          <>
            {page.sub === "getAllUser" && <UserDataTable />}
            {page.sub === "addUser" && (
              <AddNewUser edit={false} remove={false} />
            )}
            {page.sub === "editUser" && (
              <AddNewUser edit={true} remove={false} />
            )}
            {page.sub === "removeUser" && (
              <AddNewUser edit={false} remove={true} />
            )}
          </>
        )}

        {page.main === "Item" && (
          <>
            {page.sub === "getAllItem" && "<ItemDataTable />"}
            {page.sub === "addItem" && "Add Item"}
            {page.sub === "editItem" && "Edit Item"}
            {page.sub === "removeItem" && "Remove Item"}
          </>
        )}

        {page.main === "Bill" && (
          <>
            {page.sub === "getAllBill" && "<BillDataTable />"}
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
