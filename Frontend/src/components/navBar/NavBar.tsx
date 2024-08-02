import { useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";
import "../../assets/css/navBar/navBar.css";

function SubNavBar({ entity }: { entity: string }) {
  const [item, setItem] = useState<string>("getAll");

  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark" expanded>
        <Container fluid>
          <Navbar.Brand>{entity}</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link
              as={Link}
              to="/users/get"
              id="navItem"
              onClick={() => {
                setItem("getAll");
              }}
              className={item === "getAll" ? "selected" : ""}
            >
              {"Get " + entity + " List"}
            </Nav.Link>

            <Nav.Link
              as={Link}
              to="/users/add"
              id="navItem"
              onClick={() => {
                setItem("add");
              }}
              className={item === "add" ? "selected" : ""}
            >
              {"Add New " + entity}
            </Nav.Link>

            <Nav.Link
              as={Link}
              to="/users/edit"
              id="navItem"
              onClick={() => {
                setItem("edit");
              }}
              className={item === "edit" ? "selected" : ""}
            >
              {"Edit " + entity}
            </Nav.Link>

            <Nav.Link
              as={Link}
              to="/users/remove"
              id="navItem"
              onClick={() => {
                setItem("remove");
              }}
              className={item === "remove" ? "selected" : ""}
            >
              {"Remove " + entity}
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}

export default SubNavBar;
