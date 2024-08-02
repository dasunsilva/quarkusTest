import { Button, Container } from "react-bootstrap";
import useKeycloakContext from "../../services/useKeycloakContext";

const LogoutButton = () => {
  const { keycloakItem } = useKeycloakContext();

  const logout = () => {
    if (keycloakItem) {
      keycloakItem.logout();
    }
  };

  return (
    <Container id="logoutBtnContainer">
      <Button id="logoutBtn" onClick={logout}>
        Logout
      </Button>
    </Container>
  );
};

export default LogoutButton;
