document.addEventListener("DOMContentLoaded", function() {
    const prevBtn = document.querySelector(".prev-btn");
    const nextBtn = document.querySelector(".next-btn");
    const sliderContainer = document.querySelector(".slider-container");
    const sliderItems = document.querySelectorAll(".slider-item");
  
    let currentIndex = 0;
    const totalItems = sliderItems.length;
  
    // 다음 요소로 이동하는 함수
    function nextSlide() {
      currentIndex = (currentIndex + 1) % totalItems;
      updateSlider();
    }
  
    // 이전 요소로 이동하는 함수
    function prevSlide() {
      currentIndex = (currentIndex - 1 + totalItems) % totalItems;
      updateSlider();
    }
  
    // 슬라이더 업데이트 함수
    function updateSlider() {
      const itemWidth = sliderItems[0].offsetWidth;
      const newPosition = -currentIndex * itemWidth;
      sliderContainer.style.transform = `translateX(${newPosition}px)`;
    }
  
    // 이벤트 리스너 등록
    nextBtn.addEventListener("click", nextSlide);
    prevBtn.addEventListener("click", prevSlide);
  });