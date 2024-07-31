import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import "../../assets/css/navBar/mainNavBar.css";
import SubNavBar from "./NavBar";
import { Button } from "react-bootstrap";

function MainNavBar() {
  const logout = () => {
    console.log("test");
  };

  return (
    <Tabs
      defaultActiveKey="userPage"
      variant="underline"
      className="mb-3"
      id="mainNavigation"
      justify
      fill
    >
      <Tab eventKey="userPage" title="User Page">
        <SubNavBar entity="User" />
      </Tab>
      <Tab eventKey="itemPage" title="Item Page" disabled>
        <SubNavBar entity="Item" />
      </Tab>
      <Tab eventKey="billPage" title="Bill Page" disabled>
        <SubNavBar entity="Bill" />
      </Tab>
      <Tab eventKey="reportPage" title="Report Page" disabled></Tab>
      <Tab eventKey="logout" title="Log Out">
        <Button variant="outline-primary" onClick={logout}>
          Log Out
        </Button>
      </Tab>
    </Tabs>
  );
}

export default MainNavBar;
