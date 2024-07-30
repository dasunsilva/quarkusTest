import { Button } from "react-bootstrap";
import axiosInstance from "../../axios/Axios";
import { UserInput } from "../../types/UserInput";

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
  const submitData = async () => {
    console.log("Submit data:", data);

    try {
      let response;

      if (isRemove) {
        response = await axiosInstance.delete(`/users/remove/${data.userId}`);
      } else if (isEdit) {
        response = await axiosInstance.put(`/users/edit/${data.userId}`, {
          id: data.userId,
          name: data.userName,
          email: data.userEmail,
          phone: data.userPhone,
          accountNumber: data.userAccount,
        });
      } else {
        console.log(data);
        response = await axiosInstance.post("/users/add", {
          name: data.userName,
          email: data.userEmail,
          phone: data.userPhone,
          accountNumber: data.userAccount,
        });
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
  };

  return (
    <Button variant="outline-primary" id="btnPrimary" onClick={submitData}>
      Submit
    </Button>
  );
}
