import { useState } from "react";
import { Container, Button } from "react-bootstrap";
import Axios from "axios";
import { UserInput } from "../../types/UserInput";
import "../assets/css/AddNewUser.css";
import FormItem from "../form/FromItem";

function AddNewUser({ edit, remove }: { edit: boolean; remove: boolean }) {
  const [data, setData] = useState<UserInput>({
    userId: "",
    userName: "",
    userEmail: "",
    userPhone: "",
    userAccount: "",
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const submitData = () => {
    const urlBase: string = "http://localhost:8080/users/";

    Axios.post(urlBase + "add", {
      name: data.userName,
      email: data.userEmail,
      phone: data.userPhone,
      accountNumber: data.userAccount,
    })
      .then((response) => {
        console.log(response);
        resetData();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const resetData = () => {
    setData({
      userId: "",
      userName: "",
      userEmail: "",
      userPhone: "",
      userAccount: "",
    });
  };

  return (
    <>
      {!remove && edit && (
        <FormItem
          label="User ID"
          type="test"
          name="userId"
          value={data.userId}
          onChange={handleChange}
        />
      )}
      {!remove && (
        <FormItem
          label="Name"
          type="test"
          name="userName"
          value={data.userName}
          onChange={handleChange}
        />
      )}
      {!remove && (
        <FormItem
          label="Email address"
          type="email"
          name="userEmail"
          value={data.userEmail}
          onChange={handleChange}
        />
      )}
      {!remove && (
        <FormItem
          label="Mobile Number"
          type="text"
          name="userPhone"
          value={data.userPhone}
          onChange={handleChange}
        />
      )}
      {!remove && (
        <FormItem
          label="Account Number"
          type="text"
          name="userAccount"
          value={data.userAccount}
          onChange={handleChange}
        />
      )}
      {remove && !edit && (
        <FormItem
          label="User ID"
          type="test"
          name="userId"
          value={data.userId}
          onChange={handleChange}
        />
      )}

      <Container className="ButtonContainer">
        <Button variant="outline-primary" id="btnPrimary" onClick={submitData}>
          Submit
        </Button>{" "}
        <Button
          variant="outline-secondary"
          id="btnSecondary"
          onClick={resetData}
        >
          Cancel
        </Button>{" "}
      </Container>
    </>
  );
}

export default AddNewUser;
