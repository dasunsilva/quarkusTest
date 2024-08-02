import { Button } from "react-bootstrap";
import axiosInstance from "../../axios/Axios";
import { UserInput } from "../../types/UserInput";
import useKeycloakContext from "../../services/useKeycloakContext";

export default function SubmitButton({
  data,
  setData,
  isRemove,
  isEdit,
}: {
  data: UserInput;
  setData: React.Dispatch<React.SetStateAction<UserInput>>;
  isRemove: boolean;
  isEdit: boolean;
}) {
  const { keycloakItem } = useKeycloakContext();

  const submitData = async () => {
    console.log("Submit data:", data);

    if (keycloakItem && keycloakItem.token) {
      if (keycloakItem.isTokenExpired()) {
        try {
          const refreshed = await keycloakItem.updateToken(5);
          console.log(refreshed ? "Token was refreshed" : "Token is valid");
        } catch (error) {
          console.error("Failed to refresh the token:", error);
        }
      }

      const header = {
        accept: "application/json",
        authorization: `Bearer ${keycloakItem.token}`,
      };

      try {
        let response;

        if (isRemove) {
          response = await axiosInstance.delete(
            `/users/remove/${data.userId}`,
            {
              headers: header,
            }
          );
        } else if (isEdit) {
          response = await axiosInstance.put(
            `/users/edit/${data.userId}`,
            {
              id: data.userId,
              name: data.userName,
              email: data.userEmail,
              phone: data.userPhone,
              accountNumber: data.userAccount,
            },
            {
              headers: header,
            }
          );
        } else {
          console.log(data);
          response = await axiosInstance.post(
            "/users/add",
            {
              name: data.userName,
              email: data.userEmail,
              phone: data.userPhone,
              accountNumber: data.userAccount,
            },
            {
              headers: header,
            }
          );
        }

        console.log(response);
        setData({
          userId: "",
          userName: "",
          userEmail: "",
          userPhone: "",
          userAccount: "",
        });
      } catch (error) {
        console.error("Error occurred while submitting data:", error);
      }
    } else {
      console.error("Keycloak token is not available");
    }
  };

  return (
    <Button variant="outline-primary" id="btnPrimary" onClick={submitData}>
      Submit
    </Button>
  );
}
