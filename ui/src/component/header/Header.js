import '../../css/header/Header.css';

import {Container, Nav, Navbar} from "react-bootstrap";
import NavBarHeader from "./NavBarHeader";

export default function Header() {
    return (
        <Navbar collapseOnSelect expand="lg" className="header">
            <Container>
                <Navbar.Brand href="/">
                    <h1 className="header-logo">Dapamoha</h1>
                </Navbar.Brand>

                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">

                    <Nav className="me-auto"/>

                    <NavBarHeader/>

                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}