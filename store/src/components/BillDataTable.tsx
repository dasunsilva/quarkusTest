import Table from "react-bootstrap/Table";
// import { UserData } from "../types/UserData";
// import { useState } from "react";

function UserDataTable() {
  // const [data, setData] = useState<UserData>();

  return (
    <Table striped>
      <thead>
        <tr>
          <th>Bill ID</th>
          <th>Bill Date</th>
          <th>Bill Amount</th>
          <th>User ID</th>
          <th>Item IDs</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>Mark</td>
          <td>Otto</td>
          <td>@mdo</td>
          <td>12, 3</td>
        </tr>
        <tr>
          <td>2</td>
          <td>Jacob</td>
          <td>Thornton</td>
          <td>@fat</td>
          <td>12, 3</td>
        </tr>
        <tr>
          <td>3</td>
          <td>Larry the Bird</td>
          <td>Larry the Bird</td>
          <td>@twitter</td>
          <td>12, 3</td>
        </tr>
      </tbody>
    </Table>
  );
}

export default UserDataTable;
