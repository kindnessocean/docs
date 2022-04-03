import '../../css/footer/FooterCompanyInfo.css';

import {Container, Row, Col} from "react-bootstrap";

export default function FooterCompanyInfo() {
    return (
        <div className="FooterCompanyInfo">
            <Container>
                <Row>
                    <Col>
                        <h5 className="footer-company-info-logo">Dapamoha</h5>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <h7 className="footer-company-info-text">
                            © Dapamoha
                            Sitemap
                            600 Townsend Street, Suite 500
                            San Francisco, CA 94103
                            USA
                            Phone: +1 (833) 972-8766
                        </h7>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}