import Table from "react-bootstrap/Table";
import { UserData } from "../types/UserData";
import { useEffect, useState } from "react";
import axios from "axios";

function UserDataTable() {
  const [data, setData] = useState<UserData[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/users/get");

        if (response.status !== 200) {
          console.error("Error! Couldn't get data");
          return;
        }

        const users = response.data.map((e: UserData) => ({
          userId: e.userID,
          userName: e.userName,
          userEmail: e.userEmail,
          userPhone: e.userPhone,
          billIds: e.userBills,
        }));

        setData(users);
      } catch (ex) {
        console.error(ex);
      }
    };

    fetchData();
  }, []);

  return (
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
  );
}

export default UserDataTable;
