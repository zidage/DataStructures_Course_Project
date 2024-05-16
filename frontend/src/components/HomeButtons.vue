<script>
export default {
  name: "HomeButtons",
  data () {
    return {
      options: [
        {
          label: 'diyige',
          value: '1'
        },
        {
          label: 'dierge',
          value: '2'
        }
      ],
      startSelected: '',
      endSelected: ''
    }
  }
}
</script>

<template>
  <a-row style="margin-top: 30px; margin-left: 40px">
    <span style="height: 32px; line-height: 32px">出发地：</span>
    <a-select :options="options" style="width: 100px" v-model:value="startSelected"/>
    <span style="height: 32px; line-height: 32px;margin-left: 30px">目的地：</span>
    <a-select :options="options" style="width: 100px" v-model:value="endSelected"></a-select>
    <a-button type="primary" style="margin-left: 20px">开始导航</a-button>
  </a-row>
</template>

<style scoped>

</style>