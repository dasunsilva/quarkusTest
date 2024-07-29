import Table from "react-bootstrap/Table";
// import { UserData } from "../types/UserData";
// import { useState } from "react";

function UserDataTable() {
  // const [data, setData] = useState<UserData>();

  return (
    <Table striped>
      <thead>
        <tr>
          <th>Item ID</th>
          <th>Item Name</th>
          <th>Price</th>
          <th>Stock Available</th>
          <th>Bill IDs</th>
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
