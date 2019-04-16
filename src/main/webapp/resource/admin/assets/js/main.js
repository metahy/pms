$.noConflict();

jQuery(document).ready(function ($) {

    var startDate = $('#startDate');
    var endDate = $('#endDate');
    var monthDate = $('#monthDate');
    startDate.fdatepicker({
        format: 'yyyy-mm-dd'
    });
    endDate.fdatepicker({
        format: 'yyyy-mm-dd'
    });
    monthDate.fdatepicker({
        format: 'yyyy-mm'
    });
    startDate.change(function () {
        if (endDate.val() != null && endDate.val() !== '') {
            var start = new Date(startDate.val().replace(/-/g, '/'))
            var end = new Date(endDate.val().replace(/-/g, '/'))
            var num = (end - start) / (1000 * 3600 * 24);
            var days = parseInt(Math.ceil(num));
            $('#dayCount').val(days)
        }
    });
    endDate.change(function () {
        if (startDate.val() != null && startDate.val() !== '') {
            var start = new Date(startDate.val().replace(/-/g, '/'))
            var end = new Date(endDate.val().replace(/-/g, '/'))
            var num = (end - start) / (1000 * 3600 * 24);
            var days = parseInt(Math.ceil(num));
            // if (days <= 0) {
            //     end.setDate(start.getDate() + 1);
            //     var year = end.getFullYear();
            //     var month =(end.getMonth() + 1).toString();
            //     var day = (end.getDate()).toString();
            //     if (month.length === 1) {
            //         month = "0" + month;
            //     }
            //     if (day.length === 1) {
            //         day = "0" + day;
            //     }
            //     var endStr = year + "-" + month + "-" + day;
            //     $('#endDate').val(endStr)
            //     $('#dayCount').val(1)
            // } else {
            //     $('#dayCount').val(days)
            // }
            $('#dayCount').val(days <= 0 ? 1 : days)
        }
    });
    "use strict";

    [].slice.call(document.querySelectorAll('select.cs-select')).forEach(function (el) {
        new SelectFx(el);
    });

    jQuery('.selectpicker').selectpicker;


    $('.search-trigger').on('click', function (event) {
        event.preventDefault();
        event.stopPropagation();
        $('.search-trigger').parent('.header-left').addClass('open');
    });

    $('.search-close').on('click', function (event) {
        event.preventDefault();
        event.stopPropagation();
        $('.search-trigger').parent('.header-left').removeClass('open');
    });

    $('.equal-height').matchHeight({
        property: 'max-height'
    });

    // var chartsheight = $('.flotRealtime2').height();
    // $('.traffic-chart').css('height', chartsheight-122);


    // Counter Number
    $('.count').each(function () {
        $(this).prop('Counter', 0).animate({
            Counter: $(this).text()
        }, {
            duration: 3000,
            easing: 'swing',
            step: function (now) {
                $(this).text(Math.ceil(now));
            }
        });
    });


    // Menu Trigger
    $('#menuToggle').on('click', function (event) {
        var windowWidth = $(window).width();
        if (windowWidth < 1010) {
            $('body').removeClass('open');
            if (windowWidth < 760) {
                $('#left-panel').slideToggle();
            } else {
                $('#left-panel').toggleClass('open-menu');
            }
        } else {
            $('body').toggleClass('open');
            $('#left-panel').removeClass('open-menu');
        }

    });


    $(".menu-item-has-children.dropdown").each(function () {
        $(this).on('click', function () {
            var $temp_text = $(this).children('.dropdown-toggle').html();
            $(this).children('.sub-menu').prepend('<li class="subtitle">' + $temp_text + '</li>');
        });
    });


    // Load Resize
    $(window).on("load resize", function (event) {
        var windowWidth = $(window).width();
        if (windowWidth < 1010) {
            $('body').addClass('small-device');
        } else {
            $('body').removeClass('small-device');
        }

    });


});