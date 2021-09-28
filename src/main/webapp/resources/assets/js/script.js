

function showGlobalMessage(elem, timeout, messageType){
    M.toast({html: elem,displayLength:timeout,classes:messageType});
}

function showGlobalLoader(state){
    if(state){
        $('#global-loader-wrapper').show();
    }else{
        $('#global-loader-wrapper').hide();
    }
}


$(document).ready(function () {
    // document.onmousemove = handleMouseMove;

    $('[rel=tooltip]').tooltip({
        trigger: "hover"
    });

    $('[data-toggle="tooltip"]').click(function () {
        $('[data-toggle="tooltip"]').tooltip("hide");

    });

    $("#sidebarCollapse").on("click", function () {
        //$("#sidebar").toggleClass("active");
        $(this).toggleClass("active");
    });

    var myGroup = $('#sidebar');
    myGroup.on('show.bs.collapse', '.collapse', function () {
        myGroup.find('.collapse.in').collapse('hide');
    });

    new WOW().init();

    $(function () {
        $('[data-toggle="tooltip"]').tooltip({
            boundary: 'window',
            container: 'body'
        });
    })

});

function displayBigMenu(menuState) {
    if (menuState) {
        $('.side-menu').css({
            width: '240px'
        });
        $('.side-menu .side-bar a.menubutton').css({
            width: '230px',
            'text-align': 'left'
        });
        $('.side-menu .side-bar a.menubutton').attr('data-toggle', "");
        $('.side-menu .side-bar a.menubutton').attr('title', "");
        // $('.side-menu .side-bar a.menubutton').removeClass('btn-circle')
        $('.closeMenuButton').css({
            display: 'block'
        });
        $('.showMenuButton').css({
            display: 'none'
        });
    } else {
        $('.side-menu').css({
            width: '60px'
        });
        $('.side-menu .side-bar a.menubutton').css({
            width: '48px',
            'text-align': 'center'
        });
        $('.side-menu .side-bar a.menubutton').attr('data-toggle', "tooltip");
        // $('.side-menu .side-bar a.menubutton').addClass('btn-circle')
        $('.closeMenuButton').css({
            display: 'none'
        });
        $('.showMenuButton').css({
            display: 'block'
        });
    }
}

function loadDynamicPage(page) {
    // console.log(page);
    var selectedPage = page + ".html";
    if (page == '' || page == null) {
        selectedPage = '404.html'
    }
    // console.log(selectedPage);
    $("#content-wrapper").attr('src', selectedPage);
}

function signIn(state) {
    if (state) {
        $('.login-page').css({
            'top': '-100%'
        });
    } else {
        $('.login-page').css({
            'top': '0'
        });
    }
}


// // When the user clicks on the button, scroll to the top of the document
// $('#backToTop').on('click', function (e) {
//     $("html, body").animate({
//         scrollTop: $("body").offset().top
//     }, 500);
// });

function back() {
    window.history.back();
}