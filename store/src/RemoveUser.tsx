import { useState } from "react";
import "./AddUser.css";
import "./RemoveUser.css";
import Axios from "axios";
import { UserDataWithIDOnly } from "./types/UserDataWithIDOnly";

function User() {
  const urlBase: string = "http://localhost:8080/users/";
  const [userData, setUserData] = useState<UserDataWithIDOnly>({
    uID: "",
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

    Axios.delete(urlBase + "remove/" + userData.uID)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className="formContainer removeUser">
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
  );
}

export default User;
