import { ReactNode, useState } from "react";
import Keycloak from "keycloak-js";
import KeycloakContext from "./keycloakContext";

const KeycloakProvider = ({ children }: { children: ReactNode }) => {
  const [keycloakItem, setKeycloakItem] = useState<Keycloak | null>(null);

  return (
    <KeycloakContext.Provider value={{ keycloakItem, setKeycloakItem }}>
      {children}
    </KeycloakContext.Provider>
  );
};

export default KeycloakProvider;
