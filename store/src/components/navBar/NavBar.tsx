import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "../../assets/css/navBar/navBar.css";
import { Button } from "react-bootstrap";
import { useState } from "react";

function SubNavBar({
  entity,
  setPage,
}: {
  entity: string;
  setPage: (args: string) => void;
}) {
  const [item, setItem] = useState<string>("getAll");

  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark" expanded>
        <Container fluid>
          <Navbar.Brand>User</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Item
              as={Button}
              id="navItem"
              onClick={() => {
                setItem("getAll");
                setPage("getUsers");
              }}
              className={item === "getAll" ? "selected" : ""}
            >
              {"Get " + entity + " List"}
            </Nav.Item>

            <Nav.Item
              as={Button}
              id="navItem"
              onClick={() => {
                setItem("getOne");
                setPage("getOneUser");
              }}
              className={item === "getOne" ? "selected" : ""}
            >
              {"Get " + entity + " Information"}
            </Nav.Item>

            <Nav.Item
              as={Button}
              id="navItem"
              onClick={() => {
                setItem("add");
                setPage("addUser");
              }}
              className={item === "add" ? "selected" : ""}
            >
              {"Add New " + entity}
            </Nav.Item>

            <Nav.Item
              as={Button}
              id="navItem"
              onClick={() => {
                setItem("edit");
                setPage("editUser");
              }}
              className={item === "edit" ? "selected" : ""}
            >
              {"Edit " + entity}
            </Nav.Item>

            <Nav.Item
              as={Button}
              id="navItem"
              onClick={() => {
                setItem("remove");
                setPage("removeUser");
              }}
              className={item === "remove" ? "selected" : ""}
            >
              {"Remove " + entity}
            </Nav.Item>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}

export default SubNavBar;
