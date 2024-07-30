import Table from "react-bootstrap/Table";
import { UserData } from "../../types/UserData";
import { useEffect, useState } from "react";
import axios from "axios";
import { UserDTO } from "../../types/UserDTO";
import { Container } from "react-bootstrap";
import keycloak from "../../services/keycloak";

function UserDataTable() {
  const [data, setData] = useState<UserData[]>([]);

  const header = {
    accept: "application/json",
    authorization: `Bearer ${keycloak.token}`,
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/users/get", {
          headers: header,
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
      } catch (ex) {
        console.error(ex);
      }
    };

    fetchData();
  }, []);

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
