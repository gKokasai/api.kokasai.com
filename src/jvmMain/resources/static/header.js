$(function () {
    $('#hamburger_background').on('click', function () {
        $('html').removeClass('active_hamburger');
        $('#hamburger_menu').slideUp(200);
    });
});