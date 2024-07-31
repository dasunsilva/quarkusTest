import { useEffect } from "react";
import keycloak from "./Keycloak";
import useKeycloakContext from "./userKeycloakContext";

function KeycloakInstance() {
  const { setKeycloakItem } = useKeycloakContext();

  useEffect(() => {
    const initKeycloak = async () => {
      try {
        const authenticated = await keycloak.init({
          onLoad: "login-required",
          checkLoginIframe: false,
        });
        if (authenticated) {
          setKeycloakItem(keycloak);
        }
        console.log(
          `User is ${authenticated ? "authenticated" : "not authenticated"}`
        );
      } catch (error) {
        console.error("Failed to initialize adapter:", error);
      }
    };

    initKeycloak();
  }, [setKeycloakItem]);

  return null;
}

export default KeycloakInstance;
