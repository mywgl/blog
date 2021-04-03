$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide')
});

$("#newblog-container").load(/*[[@{/footer/newblog)}]]*/"/footer/newblog");
