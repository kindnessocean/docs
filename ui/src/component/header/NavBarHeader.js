import '../../css/header/NavBarHeader.css';

import {Nav} from "react-bootstrap";

export default function NavBarHeader() {
    return (
        <Nav>
            {getTextElement("Home", "/home")}
            {getTextElement("Discovery", "/discovery")}
            {getTextElement("How It works", "/how-it-works")}
            {getTextElement("Support us", "/support-us")}
            {getTextElement("Contact us", "/contact-us")}
            {getTextElement("Profile", "/profile")}
        </Nav>
    );
}

function getTextElement(lable, link){
    return (
        <Nav.Link href={link}>
            <h5 className="navBarHeader">{lable}</h5>
        </Nav.Link>
    );
}