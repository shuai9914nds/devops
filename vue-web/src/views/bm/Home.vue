<template>
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
        >
          <a-menu-item key="1">
            <a-icon type="home" />
            <span>首页</span>
          </a-menu-item>
         <a-menu-item key="2">
          <a-icon type="usergroup-add" />
            <span>用户管理</span>
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
      <a-layout-header />
      <a-layout-content style="margin: 0 16px">
        <div style="background-color: #F5F5F5; padding: 24px;">
    <a-page-header
      :ghost="false"
      title="Title"
      sub-title="This is a subtitle"
      @back="() => $router.go(-1)"
    >
      <template slot="extra">
        <a-button key="3">
          Operation
        </a-button>
        <a-button key="2">
          Operation
        </a-button>
        <a-button key="1" type="primary">
          Primary
        </a-button>
      </template>
      <a-descriptions size="small" :column="3">
        <a-descriptions-item label="Created">
          Lili Qu
        </a-descriptions-item>
        <a-descriptions-item label="Association">
          <a>421421</a>
        </a-descriptions-item>
        <a-descriptions-item label="Creation Time">
          2017-01-10
        </a-descriptions-item>
        <a-descriptions-item label="Effective Time">
          2017-10-10
        </a-descriptions-item>
        <a-descriptions-item label="Remarks">
          Gonghu Road, Xihu District, Hangzhou, Zhejiang, China
        </a-descriptions-item>
      </a-descriptions>
    </a-page-header>
  </div>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
      </a-layout-footer>
    </a-layout>
  </a-layout>
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
