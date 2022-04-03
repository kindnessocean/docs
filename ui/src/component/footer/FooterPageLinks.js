import '../../css/footer/FooterPageLinks.css';
import {Col, Container, Row} from "react-bootstrap";

let community = {
    title : "Community",
    links : [
        {
            "link" : "/open-source",
            "label": "Open Source"
        },
        {
            "link" : "/docs-&-help",
            "label": "Docs & Help"
        },
        {
            "link" : "/faq",
            "label": "FAQ"
        }
    ]
}

let company = {
    title : "Company",
    links : [
        {
            "link" : "/about-us",
            "label": "About us"
        },
        {
            "link" : "/blog",
            "label": "Blog"
        },
        {
            "link" : "/privacy",
            "label": "Privacy"
        },
        {
            "link" : "/policy-&-terms",
            "label": "Policy & Terms"
        },
        {
            "link" : "/privacy-preferences",
            "label": "Privacy preferences"
        }
    ]
}

export default function FooterPageLinks() {
    return (
        <div className="FooterPageLinks">
            <Container>
                <Row>
                    <Col>
                        <FooterPageLinksTemplate title={community.title} links={community.links} />
                    </Col>

                    <Col>
                        <FooterPageLinksTemplate title={company.title} links={company.links} />
                    </Col>
                </Row>
            </Container>

        </div>
    );
}

function FooterPageLinksTemplate({title, links}) {
    return (
        <Container>
            <Row>
                <Col>
                    <h6>{title}</h6>
                </Col>
            </Row>
            {links.map((element, i) => {
                return (
                    <Row>
                        <Col>
                            <a href={element.link}>
                                <h7>{element.label}</h7>
                            </a>
                        </Col>
                    </Row>
                );
            })}
        </Container>
    );
}