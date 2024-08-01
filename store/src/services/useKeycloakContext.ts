import { useContext } from "react";
import KeycloakContext, { KeycloakContextType } from "./keycloakContext";

const useKeycloakContext = (): KeycloakContextType => {
  const context = useContext(KeycloakContext);
  if (!context) {
    throw new Error(
      "useKeycloakContext must be used within a KeycloakProvider"
    );
  }
  return context;
};

export default useKeycloakContext;
