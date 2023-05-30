$(document).ready(function () {
    $(".ReservedArea").click(function () {
        $(".logRegListVertical").slideToggle(500);
    })
});

$(document).ready (function (){
    $("#logButton2").mouseover(function() {
            $("#noImg2").attr("src", "Logo_and_CSS/profile_icon.png");
        }
    )
});

$(document).ready (function (){
    $("#logButton2").mouseleave(function() {
            $("#noImg2").attr("src", "Logo_and_CSS/profile_icon2.png");
        }
    )
});

$(document).ready(function(){
    $(window).resize(function () {
        let win = $(this);
        let widthWindow = win.innerWidth();
        if (widthWindow <= 1000) {
            $("#menu").css({"margin-left": "1%", "width": "22%", "margin-top": "2%"});
            $("#menu > li").css({"width": "100%", "float": "none", "margin": "0"});
            $(".submenu").css({"margin-left": "-3%"});
            $(".submenu > li").css({"padding": "3%", "margin-top": "0", "margin-bottom": "0", "width": "96%"});
            $("#MenuSize1000").append($("#menu"));
        } else {
            $("#menu").css({"margin-left": "2%", "width": "90%", "margin-top": "1%"});
            $("#menu > li").css({"width": "16%", "float": "left", "margin": "2px 0 2px 1%"});
            $(".submenu").css({"margin-left": "0"});
            $(".submenu > li").css({"padding": "10% 8%", "margin-top": "2%", "margin-bottom": "2%", "width": "90%"});
            $("#corpo").prepend($("#menu"));
        }
        if (widthWindow <= 600) {
            $("#OrdineDiv600").css({"clear": "both"});
            $("#OrdineDiv600").append($("#OrdineDiv"));
        } else {
            $("#ProfileInfo").append($("#OrdineDiv"));
        }
    })
})

$(document).ready(function () {
    let win = $(this);
    let widthWindow = win.innerWidth();
    if (widthWindow <= 1000) {
        $("#menu").css({"margin-left": "1%", "width": "22%", "margin-top": "2%"});
        $("#menu > li").css({"width": "100%", "float": "none", "margin": "0"});
        $(".submenu").css({"margin-left": "-3%"});
        $(".submenu > li").css({"padding": "3%", "margin-top": "0", "margin-bottom": "0", "width": "96%"});
        $("#MenuSize1000").append($("#menu"));
    } else {
        $("#menu").css({"margin-left": "2%", "width": "90%", "margin-top": "1%"});
        $("#menu > li").css({"width": "16%", "float": "left", "margin": "2px 0 2px 1%"});
        $(".submenu").css({"margin-left": "0"});
        $(".submenu > li").css({"padding": "10% 8%", "margin-top": "2%", "margin-bottom": "2%", "width": "90%"});
        $("#corpo").prepend($("#menu"));
    }
    if (widthWindow <= 600) {
        $("#OrdineDiv600").css({"clear": "both"});
        $("#OrdineDiv600").append($("#OrdineDiv"));
    } else {
        $("#ProfileInfo").append($("#OrdineDiv"));
    }
});
