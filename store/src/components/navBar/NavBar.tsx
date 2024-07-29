import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "../../assets/css/navBar/navBar.css";
import { Button } from "react-bootstrap";
import { useState } from "react";
import { CurrentPageData } from "../../types/CurrentPageData";

function SubNavBar({
  entity,
  setPage,
}: {
  entity: string;
  setPage: (args: CurrentPageData) => void;
}) {
  const [item, setItem] = useState<string>("getAll");

  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark" expanded>
        <Container fluid>
          <Navbar.Brand>{entity}</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Item
              as={Button}
              id="navItem"
              onClick={() => {
                setItem("getAll");
                setPage({
                  main: entity,
                  sub: "getAll" + entity,
                });
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
                setPage({
                  main: entity,
                  sub: "getOne" + entity,
                });
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
                setPage({
                  main: entity,
                  sub: "add" + entity,
                });
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
                setPage({
                  main: entity,
                  sub: "edit" + entity,
                });
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
                setPage({
                  main: entity,
                  sub: "remove" + entity,
                });
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
