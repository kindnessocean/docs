import '../../css/footer/FooterExternalResourceLinks.css';

import {Col, Container, Row} from "react-bootstrap";

import DiscordIcon from "../../resource/icon/externalSite/discord.png";
import FacebookIcon from "../../resource/icon/externalSite/facebook.png";
import GitHubIcon from "../../resource/icon/externalSite/github.png";
import InstagramIcon from "../../resource/icon/externalSite/instagram.jpg";
import LinkedInIcon from "../../resource/icon/externalSite/linked.png";
import MediumIcon from "../../resource/icon/externalSite/medium.png";
import OpenCollectiveIcon from "../../resource/icon/externalSite/open-collective.png";
import PatreonIcon from "../../resource/icon/externalSite/patreon.webp";
import RedditIcon from "../../resource/icon/externalSite/reddit.png";
import TelegramIcon from "../../resource/icon/externalSite/telegram.png";
import TiktokIcon from "../../resource/icon/externalSite/tiktok.png";
import TwitterIcon from "../../resource/icon/externalSite/twitter.png";
import VkIcon from "../../resource/icon/externalSite/vk.png";
import YoutubeIcon from "../../resource/icon/externalSite/youtube.png";

const imgClassName = "externalResourceImage";
const rowClassName = "rowClassName";
const rowSeparatorClassName = "rowSeparatorClassName";
const colClassName = "colClassName";

let resource = {
    "discord" : {
        "link" : "/discord",
        "label": "discord"
    },
    "facebook" : {
        "link" : "/facebook",
        "label": "facebook"
    },
    "github" : {
        "link" : "/github",
        "label": "github"
    },
    "instagram" : {
        "link" : "/instagram",
        "label": "instagram"
    },
    "linkedin" : {
        "link" : "/linkedin",
        "label": "linkedin"
    },
    "medium" : {
        "link" : "/medium",
        "label": "medium"
    },
    "openCollective" : {
        "link" : "/open-collective",
        "label": "open-collective"
    },
    "patreon" : {
        "link" : "/patreon",
        "label": "patreon"
    },
    "reddit" : {
        "link" : "/reddit",
        "label": "reddit"
    },
    "telegram" : {
        "link" : "/telegram",
        "label": "telegram"
    },
    "tiktok" : {
        "link" : "/tiktok",
        "label": "tiktok"
    },
    "twitter" : {
        "link" : "/twitter",
        "label": "twitter"
    },
    "vk" : {
        "link" : "/vk",
        "label": "vk"
    },
    "youtube" : {
        "link" : "/youtube",
        "label": "youtube"
    },
}

export default function FooterExternalResourceLinks() {

    return (
        <Container>

            <Row className={rowSeparatorClassName}/>

            <Row className={rowClassName}>
                {getCol(resource.tiktok.link, TiktokIcon, resource.tiktok.label)}
                {getCol(resource.github.link, GitHubIcon, resource.github.label)}
                {getCol(resource.telegram.link, TelegramIcon, resource.telegram.label)}
                {getCol(resource.youtube.link, YoutubeIcon, resource.youtube.label)}
                {getCol(resource.discord.link, DiscordIcon, resource.discord.label)}
                {getCol(resource.linkedin.link, LinkedInIcon, resource.linkedin.label)}
                {getCol(resource.vk.link, VkIcon, resource.vk.label)}
            </Row>

            <Row className={rowSeparatorClassName}/>

            <Row className={rowClassName}>
                {getCol(resource.twitter.link, TwitterIcon, resource.twitter.label)}
                {getCol(resource.reddit.link, RedditIcon, resource.reddit.label)}
                {getCol(resource.medium.link, MediumIcon, resource.medium.label)}
                {getCol(resource.instagram.link, InstagramIcon, resource.instagram.label)}
                {getCol(resource.facebook.link, FacebookIcon, resource.facebook.label)}
                {getCol(resource.openCollective.link, OpenCollectiveIcon, resource.openCollective.label)}
                {getCol(resource.patreon.link, PatreonIcon, resource.patreon.label)}
            </Row>

            <Row className={rowSeparatorClassName}/>

        </Container>
    );
}

function getCol(href, src, alt){
    return (
        <Col className={colClassName}>
            <a href={href}>
                <img
                    className={imgClassName}
                    src={src}
                    alt={alt}
                />
            </a>
        </Col>
    );
}