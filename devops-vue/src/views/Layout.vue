<template>
  <div id="app">
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
      <a-layout-sider v-model="collapsed" collapsible>
        <div style="width: 200px">
          <div class="logo" />
          <a-menu
                  :default-selected-keys="[$route.path]"
                  mode="inline"
                  theme="dark"
                  :inline-collapsed="collapsed"
                  @click="handleClick"
          >
            <a-menu-item v-if="permissionMenus.includes('Home')" key="/Home">
              <a-icon type="home"/>
              <span>首页</span>
            </a-menu-item>

            <a-menu-item v-if="permissionMenus.includes('User')" key="/User">
              <a-icon type="usergroup-add"/>
              <span>用户管理</span>
            </a-menu-item>

            <a-sub-menu v-if="permissionMenus.includes('Role') || permissionMenus.includes('UserPerm')">
              <span slot="title">
                <a-icon type="solution"/>
                <span>权限管理</span>
              </span>

              <a-menu-item v-if="permissionMenus.includes('Role')" key="/Role">
                <a-icon type="user"/>
                <span>角色管理</span>
              </a-menu-item>
              <a-menu-item v-if="permissionMenus.includes('UserPerm')" key="/UserPerm">
                <a-icon type="user-add"/>
                <span>分配权限</span>
              </a-menu-item>
            </a-sub-menu>

            <a-menu-item v-if="permissionMenus.includes('Monitor')" key="/Monitor">
              <a-icon type="usergroup-add"/>
              <span>监控管理</span>
            </a-menu-item>
          </a-menu>
        </div>
      </a-layout-sider>
      <a-layout>
        <a-layout-header style="background: #fff; padding: 0">
          <div class="header">
            <a-row>
              <a-col :span="3">
                <a-icon
                        class="trigger"
                        :type="collapsed ? 'menu-unfold' : 'menu-fold'"
                        @click="() => (collapsed = !collapsed)"
                />
              </a-col>
              <a-col :span="3" :offset="18">
                <div class="item">
                  <a-dropdown>
                    <div class="button" @click="(e) => e.preventDefault()">
                      {{ userInfo.name }}
                    </div>
                    <a-menu slot="overlay">
                      <a-menu-item>
                        <a href="javascript:;" @click="logout">
                          <a-icon
                                  style="margin-right: 12px; min-width: 12px"
                                  type="logout"
                          />
                          <span>退出登录</span>
                        </a>
                      </a-menu-item>
                    </a-menu>
                  </a-dropdown>
                </div>
              </a-col>
            </a-row>
          </div>
        </a-layout-header>
        <a-layout-content
                :style="{
            margin: '24px 16px',
            padding: '24px',
            background: '#fff',
            minHeight: '280px',
          }"
        >
          <a-spin :spinning="isLoading" tip="加载中...">
            <router-view/>
          </a-spin>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<script>
  import {mapGetters, mapMutations} from "vuex";
  import Http from "@/utils/http";

  export default {
    data() {
      return {
        collapsed: false,
        userInfo: {},
        permissionMenus: []
      };
    },
    computed: {
      ...mapGetters(["isLoading"]),
    },
    mounted() {

    },
    created() {
      this.getUserInfo();

    },

    methods: {
      ...mapMutations(["SET_LOADING"]),
      toggleCollapsed() {
        this.collapsed = !this.collapsed;
      },
      handleClick({key}) {
        this.SET_LOADING(false);
        this.$router.push({path: key});
      },
      logout() {
        Http({
          url: "/login/out",
          method: "get",
          success: (response) => {
            localStorage.clear();
            this.$router.push({path: "/"});
          },
        });
      },
      getUserInfo() {
        Http({
          url: "/user/one",
          method: "get",
          success: (response) => {
            console.log(response);
            this.userInfo = response;
            this.resetMenu(this.userInfo.uid)
          },
        });
      },
      resetMenu(uid) {
        Http({
          url: "/menu/list/" + uid,
          method: "get",
          success: (response) => {
            this.permissionMenus = response.map(({component}) => component)
          }
        })
      }
    },
  };
</script>
<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
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

.header {
  width: 100%;

  .item {
    width: 100%;
    height: 64px;
    box-sizing: border-box;
    text-align: center;
    cursor: pointer;

    .button {
      width: 100%;
      height: 64px;
    }
  }

  .item:hover {
    background-color: #fafafa;
  }
}
</style>
