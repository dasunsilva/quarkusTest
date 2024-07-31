import { createContext } from "react";
import Keycloak from "keycloak-js";

export interface KeycloakContextType {
  keycloakItem: Keycloak | null;
  setKeycloakItem: (keycloak: Keycloak) => void;
}

const KeycloakContext = createContext<KeycloakContextType | undefined>(
  undefined
);

export default KeycloakContext;
