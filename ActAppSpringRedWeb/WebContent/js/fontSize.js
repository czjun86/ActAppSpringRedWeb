// JavaScript Document
var _height=$(window).height(),_width=$(window).width();
var isDesktop = navigator['userAgent'].match(/(ipad|iphone|ipod|android|windows phone)/i) ? false : true;
var fontunit = isDesktop ? 10 : ((_width>_height?_height:_width)/320)*10;
//document.write('<style type="text/css">'+'html,body {font-size:'+ (fontunit<30?fontunit:'30')+'px;}'+'</style>');
$(function() {
    if(fontunit > 30 && fontunit < 40){
        fontunit = 30;
    }else if (fontunit > 40){
        fontunit = 42;
    }
    $('body').css('font-size',fontunit+'px' );
});