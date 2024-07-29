import { Button } from "react-bootstrap";
import { UserInput } from "../../types/UserInput";

export default function ResetButton({
  setData,
}: {
  setData: React.Dispatch<React.SetStateAction<UserInput>>;
}) {
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
    <Button variant="outline-secondary" id="btnSecondary" onClick={resetData}>
      Cancel
    </Button>
  );
}
