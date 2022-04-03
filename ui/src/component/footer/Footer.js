import {Container, Row, Col} from "react-bootstrap";

import '../../css/footer/Footer.css';

import FooterCompanyInfo from "./FooterCompanyInfo";
import FooterPageLinks from "./FooterPageLinks";
import FooterExternalResourceLinks from "./FooterExternalResourceLinks";

export default function Footer() {
    return (
        <div className="Footer">
            <Container>
                <Row>
                    <Col>
                        <FooterCompanyInfo/>
                    </Col>
                    <Col>
                        <FooterPageLinks/>
                    </Col>
                    <Col>
                        <FooterExternalResourceLinks/>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}