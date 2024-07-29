import { useState } from "react";
import AddUser from "./AddUser";
import GetUserData from "./GetUserData";
import EditUser from "./EditUser";
import RemoveUser from "./RemoveUser";

function UserHome() {
  const [currentTab, setCurrentTab] = useState("get");

  const handleClick = (btn: string) => {
    console.log(btn);
    setCurrentTab(btn);
  };

  return (
    <>
      <h2 className="mainHeader">User Page</h2>
      <div className="mainContainer">
        <div className="buttonArea">
          <div className="btnContainer">
            <button
              className={currentTab === "get" ? "active" : ""}
              onClick={() => handleClick("get")}
            >
              Get User Info
            </button>
          </div>
          <div className="btnContainer">
            <button
              className={currentTab === "add" ? "active" : ""}
              onClick={() => handleClick("add")}
            >
              Add New User
            </button>
          </div>
          <div className="btnContainer">
            <button
              className={currentTab === "edit" ? "active" : ""}
              onClick={() => handleClick("edit")}
            >
              Edit User Info
            </button>
          </div>
          <div className="btnContainer">
            <button
              className={currentTab === "remove" ? "active" : ""}
              onClick={() => handleClick("remove")}
            >
              Remove a User
            </button>
          </div>
        </div>
        <div className="contentArea">
          {currentTab === "get" && <GetUserData />}
          {currentTab === "add" && <AddUser />}
          {currentTab === "edit" && <EditUser />}
          {currentTab === "remove" && <RemoveUser />}
        </div>
      </div>
    </>
  );
}

export default UserHome;
