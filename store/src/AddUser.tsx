import { useState } from "react";
import "./assets/css/AddUser.css";
import Axios from "axios";
import { UserData } from "./types/UserData";

function User() {
  const urlBase: string = "http://localhost:8080/users/";
  const [userData, setUserData] = useState<UserData>({
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

  const handleAddUser = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("Form submitted with data:", userData);

    Axios.post(urlBase + "add", {
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
    <div className="formContainer">
      <form className="form" onSubmit={handleAddUser}>
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
