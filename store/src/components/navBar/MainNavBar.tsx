import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import "../../assets/css/navBar/mainNavBar.css";
import SubNavBar from "./NavBar";
// import useKeycloakContext from "../../services/userKeycloakContext";

function MainNavBar() {
  // const { keycloakItem } = useKeycloakContext();

  const logout = () => {
    console.log("Test");
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
      <Tab title="Log Out" onClick={logout}></Tab>
    </Tabs>
  );
}

export default MainNavBar;
