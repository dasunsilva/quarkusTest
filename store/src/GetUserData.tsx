import { useState } from "react";
import "./assets/css/AddUser.css";
import "./assets/css/GetUserData.css";
import Axios from "axios";
import { UserDataWithID } from "./types/UserDataWithID.ts";

function User() {
  const urlBase: string = "http://localhost:8080/users/";
  const [userData, setUserData] = useState<UserDataWithID>({
    uID: "",
    uName: "",
    uEmail: "",
    uPhone: "",
    uBillIDs: "",
  });

  const handleFormInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setUserData((prevUserData) => ({
      ...prevUserData,
      [name]: value,
    }));
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("Form submitted with data:", userData);

    Axios.get(urlBase + "get/" + String(userData.uID))
      .then(function (response) {
        console.log(response);
        setUserData({
          uID: response.data.id,
          uName: response.data.name,
          uEmail: response.data.email,
          uPhone: response.data.phone,
          uBillIDs: response.data.billIDs,
        });
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className="dataContainer">
      <div className="formContainer userIdContainer">
        <form className="formGetUser" onSubmit={handleSubmit}>
          <div className="formGroup">
            <label htmlFor="userName" className="formLabel">
              User ID
            </label>
            <span className="symbolColon"> : </span>
            <input
              type="text"
              id="userName"
              name="uID"
              className="formInput"
              onChange={handleFormInput}
              value={userData.uID}
            />
          </div>
          <div className="formGroup">
            <button className="submitBtn" type="submit">
              Submit
            </button>
          </div>
        </form>
      </div>
      <div className="userInfoContainer">
        <form className="form" onSubmit={handleSubmit}>
          <div className="formGroup">
            <label htmlFor="userName" className="formLabel">
              Name
            </label>
            <span className="symbolColon"> : </span>
            <input
              type="text"
              id="userName"
              name="uName"
              className="formInput"
              onChange={handleFormInput}
              value={userData.uName}
              readOnly
            />
          </div>

          <div className="formGroup">
            <label htmlFor="email" className="formLabel">
              Email
            </label>
            <span className="symbolColon"> : </span>
            <input
              type="email"
              id="email"
              name="uEmail"
              className="formInput"
              onChange={handleFormInput}
              value={userData.uEmail}
              readOnly
            />
          </div>

          <div className="formGroup">
            <label htmlFor="phoneNumber" className="formLabel">
              Phone Number
            </label>
            <span className="symbolColon"> : </span>
            <input
              type="text"
              id="phoneNumber"
              name="uPhone"
              className="formInput"
              onChange={handleFormInput}
              value={userData.uPhone}
              readOnly
            />
          </div>

          <div className="formGroup">
            <label htmlFor="accountNumber" className="formLabel">
              Bill IDs
            </label>
            <span className="symbolColon"> : </span>
            <input
              type="text"
              id="accountNumber"
              name="uBillIDs"
              className="formInput"
              onChange={handleFormInput}
              value={userData.uBillIDs}
              readOnly
            />
          </div>
        </form>
      </div>
    </div>
  );
}

export default User;
