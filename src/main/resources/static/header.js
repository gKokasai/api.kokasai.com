$(function(){
    $('#hamburger_icon').on('click', function() {
        if($('html').hasClass('active_hamburger')){
            $('#hamburger_menu').slideUp(200);
        } else {
            $('#hamburger_menu').slideDown(500);
        }
        $('html').toggleClass('active_hamburger');
        return false;
    });
    $('#hamburger_menu .menu_element a').on('click', function(e){
        $('html').removeClass('active_hamburger');
        $('#hamburger_menu').slideUp(200);
        e.preventDefault();
        url = $(this).attr('href');
        if(url != ''){
            setTimeout(function(){
                window.location = url;
            }, 250);
        }
        return false;
    });
    $('#hamburger_background').on('click', function(){
        $('html').removeClass('active_hamburger');
        $('#hamburger_menu').slideUp(200);
    });
});