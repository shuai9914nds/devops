<template>
  <div>home</div>
</template>
<script>
export default {
  data() {
    return {
    };
  },
  methods: {
    toggleCollapsed() {
      this.collapsed = !this.collapsed;
    },
    handleClick(e) {
      console.log(e);
      this.$router.push({path: e.key})
    },
  },
};
</script>

<style>
#components-layout-demo-side .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
</style>
