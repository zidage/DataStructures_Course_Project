<script>
export default {
  name: "HomeMap"
}
</script>

<template>
  <div style="display: flex;flex-direction: column">
    <div>地图</div>
    <div class="main"></div>
  </div>
</template>

<style scoped>
.main {
  width: 800px;
  height: 600px;
  border: red 1px solid;
}
</style>