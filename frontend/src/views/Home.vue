<script>
import HomeButtons from "@/components/HomeButtons.vue";
import HomeMap from "@/components/HomeMap.vue";
import HomeSuggestions from "@/components/HomeSuggestions.vue";
import image1 from '../img/1.jpg';
import image2 from '../img/2.jpg';
import image3 from '../img/3.jpg';
export default {
  name: "HomePage",
  components: {HomeSuggestions, HomeMap, HomeButtons,},
  methods: {
    onChange(current) {
      console.log(current);
    },
    goToSlide(index) {
      this.currentSlide = index;
    }
  },
  data() {
    return {
      currentSlide: 0,
      slides: [
      { image: image1 },
  { image: image2 },
  { image: image3 }
      ],
      listItems: [
        { id: 1, title: '标题1', text: '这里是详细内容...', image: 'https://via.placeholder.com/150' },
        { id: 2, title: '标题2', text: '这里是更多的详细内容...', image: 'https://via.placeholder.com/150' },
        // Add more items as needed
      ]
    };
  }
}
</script>



<template>
  <div class="content-wrapper">
    <div class="carousel-container">
      <div class="carousel" :style="{transform: `translateX(-${currentSlide * 100}%)`}">
        <div class="slide" v-for="(slide, index) in slides" :key="index">
          <img :src="slide.image" :alt="`Slide ${index + 1}`">
        </div>
      </div>
      <div class="dots">
        <span 
          class="dot" 
          v-for="(slide, index) in slides" 
          :key="'dot-' + index"
          :class="{ active: currentSlide === index }"
          @click="goToSlide(index)"
        ></span>
      </div>
    </div>
    <div class="weather-block">
      <!-- Example Static Content, replace with dynamic content as needed -->
      <h2>Today's Weather</h2>
      <p><strong>City:</strong> Example City</p>
      <p><strong>Temperature:</strong> 23°C</p>
      <p><strong>Condition:</strong> Partly Cloudy</p>
    </div>
  </div>

  <div class="list-container">
    <div class="list-item" v-for="item in listItems" :key="item.id">
      <div class="item-image">
        <img :src="item.image" alt="List item image">
      </div>
      <div class="item-content">
        <h3>{{ item.title }}</h3>
        <p>{{ item.text }}</p>
      </div>
    </div>
  </div>

</template>

<style scoped>
.content-wrapper {
  display: flex;
  justify-content: space-between;
  width: 80%; /* Container takes up 80% of the window width */
  margin: auto; /* Center the container */
}

.carousel-container {
  flex: 0 0 80%; /* 80% of the content wrapper */
  position: relative;
  overflow: hidden;
  background-color: #007BFF;
  aspect-ratio: 16 / 9; /* Sets the width to height ratio */
}

.carousel {
  display: flex;
  width: 100%; /* Ensures the carousel matches the width of the container */
  height: 100%; /* Ensures the carousel matches the height of the container */
  transition: transform 0.5s ease;
}

.slide {
  flex: 0 0 100%;
  width: 100%;
  height: 100%;
}

img {
  width: 100%;
  height: 100%;
  display: block;
  object-fit: cover;
}

.dots {
  text-align: center;
  padding: 10px 0;
  position: absolute;
  width: 100%;
  bottom: 0;
  background-color: transparent; /* Changed from semi-transparent gray to transparent */
}


.dot {
  height: 10px;
  width: 10px;
  margin: 0 5px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.dot.active {
  background-color: #fff;
}

.weather-block {
  flex: 0 0 20%; /* 20% of the content wrapper, maintaining a 4:1 ratio with the carousel */
  padding: 20px;
  background-color: #f8f8f8;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.weather-block h2 {
  margin-top: 0;
}

/* 列表样式 */
.list-container {
  width: 80%; /* Same as the carousel container width */
  margin: auto;
  margin-top: 40px;
  background-color: #f0f0f0; /* Light gray background similar to the one in your example */
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* Soft shadow for some depth */
}

.list-item {
  display: flex;
  align-items: center; /* Aligns the image and the content vertically */
  margin-bottom: 20px; /* Space between items */
}

.item-image img {
  width: 150px; /* Fixed width for images */
  height: 150px; /* Fixed height for images */
  margin-right: 20px; /* Space between image and text */
  object-fit: cover; /* Ensures images cover the area, useful for different aspect ratios */
}

.item-content {
  flex: 1; /* Takes up remaining space */
}

.item-content h3 {
  margin-top: 0;
}

.item-content p {
  margin: 0;
}
</style>