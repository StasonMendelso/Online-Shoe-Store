let all = document.querySelectorAll("div.product__size__container button:enabled");
all.forEach(value => {
    value.addEventListener('click', selectSizeButton);
});

function selectSizeButton(e) {
    all.forEach(value => value.classList.remove("active"));

    e.target.classList.add("active");
}

let thumbnails = document.querySelectorAll("div.product__thumbnails__container div.swiper-wrapper div.product__thumbnails__swiper__slide");

let mainSwiper = new Swiper('.product__swiper__wrapper', {
    loop: true,

    // Navigation arrows
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    slidesPerView: 1,
});

thumbnails.forEach(value => {
    value.addEventListener('click', function (e) {
        let index = value.getAttribute('data-index');
        mainSwiper.slideToLoop(index);
    });
    let index = value.getAttribute('data-index');
    value.classList.remove('active');
    if (index == mainSwiper.realIndex) {
        value.classList.add('active');
    }
});
let thumbnailSwiper = new Swiper('.product__thumbnails__container', {
    direction: 'vertical',

    spaceBetween: 30,
    slidesPerView: 5,
    mousewheel: {
        sensitivity: 1
    },
    scrollbar: {
        el: '.swiper-scrollbar',
        draggable: true
    }
});
mainSwiper.on('slideChange', function () {
    console.log('slide changed');
    thumbnails.forEach(value => {
        let index = value.getAttribute('data-index');
        value.classList.remove('active');
        if (index == mainSwiper.realIndex) {
            value.classList.add('active');
            thumbnailSwiper.slideTo(index - 2);
        }
    })
});






