import { useState } from "react";
import { Button, Container } from "react-bootstrap";
import "../../assets/css/AddNewUser.css";
import { UserInput } from "../../types/UserInput";
import ResetButton from "../button/ResetButton";
import SubmitButton from "../button/SubmitButton";
import FormItem from "../form/FromItem";
import useKeycloakContext from "../../services/useKeycloakContext";

function AddNewUser({ edit, remove }: { edit: boolean; remove: boolean }) {
  const { keycloakItem } = useKeycloakContext();

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

  const logout = () => {
    if (keycloakItem) {
      keycloakItem.logout();
    }
  };

  return (
    <Container id="UIContainer">
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
        <SubmitButton
          data={data}
          setData={setData}
          isRemove={remove}
          isEdit={edit}
        />
        <ResetButton setData={setData} />
      </Container>
      <Container id="logoutBtnContainer">
        <Button id="logoutBtn" onClick={logout}>
          Logout
        </Button>
      </Container>
    </Container>
  );
}

export default AddNewUser;
