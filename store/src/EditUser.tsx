import { useState } from "react";
import "./AddUser.css";
import "./EditUser.css";
import Axios from "axios";
import { UserDataWithIDAcc } from "./types/UserDataWithIDAcc";

function User() {
  const urlBase: string = "http://localhost:8080/users/";
  const [userData, setUserData] = useState<UserDataWithIDAcc>({
    uID: "",
    uName: "",
    uEmail: "",
    uPhone: "",
    uAccount: "",
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

    Axios.put(urlBase + "edit/" + userData.uID, {
      id: userData.uID,
      name: userData.uName,
      email: userData.uEmail,
      phone: userData.uPhone,
      accountNumber: userData.uAccount,
    })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className="formContainer editUser">
      <form className="form" onSubmit={handleSubmit}>
        <div className="formGroup">
          <label htmlFor="userName" className="formLabel">
            User ID
          </label>
          <span className="symbolColon"> : </span>
          <input
            type="text"
            id="userID"
            name="uID"
            className="formInput"
            onChange={handleFormInput}
            value={userData.uID}
          />
        </div>

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
          />
        </div>

        <div className="formGroup">
          <label htmlFor="accountNumber" className="formLabel">
            Account Number
          </label>
          <span className="symbolColon"> : </span>
          <input
            type="text"
            id="accountNumber"
            name="uAccount"
            className="formInput"
            onChange={handleFormInput}
            value={userData.uAccount}
          />
        </div>
        <div className="formGroup">
          <button className="submitBtn" type="submit">
            Submit
          </button>
        </div>
      </form>
    </div>
  );
}

export default User;
