$(function () {
    $('#hamburger_menu .menu_element a').on('click', function (e) {
        $('html').removeClass('active_hamburger');
        $('#hamburger_menu').slideUp(200);
        e.preventDefault();
        let url = $(this).attr('href');
        if (url !== '') {
            setTimeout(function () {
                window.location = url;
            }, 250);
        }
        return false;
    });
    $('#hamburger_background').on('click', function () {
        $('html').removeClass('active_hamburger');
        $('#hamburger_menu').slideUp(200);
    });
});