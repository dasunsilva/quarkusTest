import { useState } from "react";
import { Container } from "react-bootstrap";
import "../../assets/css/AddNewUser.css";
import { UserInput } from "../../types/UserInput";
import LogoutButton from "../button/LogoutButton";
import ResetButton from "../button/ResetButton";
import SubmitButton from "../button/SubmitButton";
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

  return (
    <>
      <Container id="UIContainer">
        {!remove && edit && (
          <FormItem
            label="User ID"
            type="text"
            name="userId"
            id="userID"
            value={data.userId}
            onChange={handleChange}
          />
        )}
        {!remove && (
          <FormItem
            label="Name"
            type="text"
            id="userName"
            name="userName"
            value={data.userName}
            onChange={handleChange}
          />
        )}
        {!remove && (
          <FormItem
            label="Email address"
            id="userEmail"
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
            id="userPhone"
            name="userPhone"
            value={data.userPhone}
            onChange={handleChange}
          />
        )}
        {!remove && (
          <FormItem
            label="Account Number"
            type="text"
            id="userAccount"
            name="userAccount"
            value={data.userAccount}
            onChange={handleChange}
          />
        )}
        {remove && !edit && (
          <FormItem
            label="User ID"
            type="text"
            name="userId"
            id="userID"
            value={data.userId}
            onChange={handleChange}
          />
        )}

        <Container className="ButtonContainer">
          <SubmitButton
            data={data}
            setData={setData}
            isRemove={remove}
            isEdit={edit}
          />
          <ResetButton setData={setData} />
        </Container>
      </Container>
      <LogoutButton />
    </>
  );
}

export default AddNewUser;
