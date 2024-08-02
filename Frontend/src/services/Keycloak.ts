import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:8082/",
  realm: "new-realm",
  clientId: "front-end-client",
});

export default keycloak;
