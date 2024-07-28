import RemoveUser from "./RemoveUser";

function UserHome() {
  return (
    <>
      <h2 className="mainHeader">User Page</h2>
      <div className="mainContainer">
        <div className="buttonArea">
          <div className="btnContainer">
            <button>Get User Info</button>
          </div>
          <div className="btnContainer">
            <button>Add New User</button>
          </div>
          <div className="btnContainer">
            <button>Edit User Info</button>
          </div>
          <div className="btnContainer">
            <button className="active">Remove a User</button>
          </div>
        </div>
        <div className="contentArea">
          <RemoveUser />
        </div>
      </div>
    </>
  );
}

export default UserHome;
