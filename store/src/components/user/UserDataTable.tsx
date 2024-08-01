import axios from "axios";
import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import Table from "react-bootstrap/Table";
import { UserData } from "../../types/UserData";
import { UserDTO } from "../../types/UserDTO";
import useKeycloakContext from "../../services/useKeycloakContext";

function UserDataTable() {
  const { keycloakItem } = useKeycloakContext();
  const [data, setData] = useState<UserData[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (keycloakItem && keycloakItem.token) {
          const response = await axios.get("http://localhost:8080/users/get", {
            headers: {
              accept: "application/json",
              authorization: `Bearer ${keycloakItem.token}`,
            },
          });

          if (response.status !== 200) {
            console.error("Error! Couldn't get data");
            return;
          }
          const users = response.data.map((e: UserDTO) => ({
            userID: e.id,
            userName: e.name,
            userEmail: e.email,
            userPhone: e.phone,
            userBills: e.billIDs.join(", "),
          }));

          console.log(users);

          setData(users);
        } else {
          console.error("No Keycloak token available");
        }
      } catch (ex) {
        console.error(ex);
      }
    };

    fetchData();
  }, [keycloakItem]);

  return (
    <Container id="UIContainer">
      <Table striped>
        <thead>
          <tr>
            <th>User ID</th>
            <th>User Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Bill IDs</th>
          </tr>
        </thead>
        <tbody>
          {data.map((user) => (
            <tr key={user.userID}>
              <td>{user.userID}</td>
              <td>{user.userName}</td>
              <td>{user.userPhone}</td>
              <td>{user.userEmail}</td>
              <td>{user.userBills}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}

export default UserDataTable;
