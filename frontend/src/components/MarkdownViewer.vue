<script>
import MarkdownIt from "markdown-it";

export default {
  name: "MarkdownViewer",
  props: {
    markdown: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      html: ''
    }
  },
  created () {
    this.html = new MarkdownIt().render(this.markdown)
  }
}
</script>

<template>
  <div v-html="html"></div>
</template>

<style scoped>
</style>