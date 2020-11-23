<template>
  <div id="app">
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
      <a-layout-sider v-model="collapsed" collapsible>
        <div style="width: 200px">
          <div class="logo" />
          <a-menu
            :default-selected-keys="['1']"
            :default-open-keys="['sub1']"
            mode="inline"
            theme="dark"
            :inline-collapsed="collapsed"
            @click="handleClick"
          >
            <a-menu-item key="home">
              <a-icon type="home" />
              <span>首页</span>
            </a-menu-item>
            <a-menu-item key="/user/User">
              <a-icon type="usergroup-add" />
              <span>User</span>
            </a-menu-item>
            <a-sub-menu key="sub1">
              <span slot="title"
                ><a-icon type="mail" /><span>权限管理</span></span
              >
              <a-menu-item key="5"> 角色管理</a-menu-item>
              <a-menu-item key="6"> 用户授权</a-menu-item>
              <a-menu-item key="7"> Option 7 </a-menu-item>
              <a-menu-item key="8"> Option 8 </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub2">
              <span slot="title"
                ><a-icon type="appstore" /><span>Navigation Two</span></span
              >
              <a-menu-item key="9"> Option 9 </a-menu-item>
              <a-menu-item key="10"> Option 10 </a-menu-item>
              <a-sub-menu key="sub3" title="Submenu">
                <a-menu-item key="11"> Option 11 </a-menu-item>
                <a-menu-item key="12"> Option 12 </a-menu-item>
              </a-sub-menu>
            </a-sub-menu>
          </a-menu>
        </div>
      </a-layout-sider>
      <a-layout>
        <a-layout-header style="background: #fff; padding: 0">
          <a-icon
            class="trigger"
            :type="collapsed ? 'menu-unfold' : 'menu-fold'"
            @click="() => (collapsed = !collapsed)"
          />
        </a-layout-header>
        <a-layout-content
          :style="{
            margin: '24px 16px',
            padding: '24px',
            background: '#fff',
            minHeight: '280px',
          }"
        >
          <router-view />
        </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>
<script>
export default {
  data() {
    return {
      collapsed: false,
    };
  },
  methods: {
    toggleCollapsed() {
      this.collapsed = !this.collapsed;
    },
    handleClick({ key }) {
      this.$router.push({ path: key });
    },
  },
};
</script>
<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
