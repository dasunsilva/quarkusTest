import { Axios } from "axios";
import { Button } from "react-bootstrap"

export default function SubmitButton({data, isRemove, isEdit} : {data : <UserInput>, isRemove : boolean, isEdit : boolean}) {

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
        // resetData();
      })
      .catch((error) => {
        console.log(error);
      });
  };
    return (
      <Button variant="outline-primary" id="btnPrimary" onClick={submitData}>
          Submit
        </Button>{" "}
  );
}
