<template>
  <div id="home">
    <Login/>
  </div>
</template>
<script>
import Login from "@/components/Login";

export default {
  name: 'App',
  components: {
    Login
  }
}
</script>

<style>
#home {
  text-align: center;
  margin-top: 60px;
}
</style>
