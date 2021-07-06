@file:JsModule("react-share")
@file:JsNonModule

import react.RClass
import react.RProps

@JsName("EmailIcon")
external val emailIcon: RClass<IconProps>

@JsName("EmailShareButton")
external val emailShareButton: RClass<ShareButtonProps>

@JsName("LineIcon")
external val lineIcon: RClass<IconProps>

@JsName("LineShareButton")
external val lineShareButton: RClass<ShareButtonProps>

@JsName("TwitterIcon")
external val twitterIcon: RClass<IconProps>

@JsName("TwitterShareButton")
external val twitterShareButton: RClass<ShareButtonProps>

@JsName("FacebookIcon")
external val facebookIcon: RClass<IconProps>

@JsName("FacebookShareButton")
external val facebookShareButton: RClass<ShareButtonProps>

external interface ShareButtonProps : RProps {
    var url: String
}

external interface IconProps : RProps {
    var size: Int
    var round: Boolean
}
