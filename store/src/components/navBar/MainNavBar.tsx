import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import "../../assets/css/navBar/mainNavBar.css";
import SubNavBar from "./NavBar";

function MainNavBar({ setPageFn }: { setPageFn: (arg: string) => void }) {
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
        <SubNavBar setPage={setPageFn} entity="User" />
      </Tab>
      <Tab eventKey="itemPage" title="Item Page">
        <SubNavBar setPage={setPageFn} entity="Item" />
      </Tab>
      <Tab eventKey="billPage" title="Bill Page">
        <SubNavBar setPage={setPageFn} entity="Bill" />
      </Tab>
      <Tab eventKey="reportPage" title="Report Page"></Tab>
    </Tabs>
  );
}

export default MainNavBar;
