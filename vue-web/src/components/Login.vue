<template>
  <a-form
          id="components-form-demo-normal-login"
          :form="form"
          class="login-form"
          @submit="handleSubmit"
  >
    <a-form-item>
      <a-input
              v-decorator="[
          'username',
          {
            rules: [{ required: true, message: 'Please input your username!' }],
          },
        ]"
              placeholder="Username"
      >
        <a-icon slot="prefix" type="user" style="color: rgba(0, 0, 0, 0.25)"/>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input
              v-decorator="[
          'password',
          {
            rules: [{ required: true, message: 'Please input your Password!' }],
          },
        ]"
              type="password"
              placeholder="Password"
      >
        <a-icon slot="prefix" type="lock" style="color: rgba(0, 0, 0, 0.25)"/>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-row>
        <a-col :span="20">
          <a-input
                  v-decorator="[
              'identifyCode',
              {
                rules: [{ required: true, message: '验证码不能为空!' }],
              },
            ]"
                  placeholder="验证码"
          >
            <a-icon
                    slot="prefix"
                    type="code"
                    style="color: rgba(0, 0, 0, 0.25)"
            />
          </a-input>
        </a-col>
        <a-col :span="4">
          <img :src="this.identifyImg" @click="getCode"/>
        </a-col>
      </a-row>
    </a-form-item>
    <a-form-item>
      <a-checkbox
              v-decorator="[
          'remember',
          {
            valuePropName: 'checked',
            initialValue: true,
          },
        ]"
      >
        Remember me
      </a-checkbox>
      <a class="login-form-forgot" href=""> Forgot password </a>
      <a-button type="primary" html-type="submit" class="login-form-button">
        Log in
      </a-button>
      Or
      <a href=""> register now! </a>
    </a-form-item>
  </a-form>
</template>

<script>
  export default {
    data() {
      return {
        identifyImg: "",
      };
    },
    beforeCreate() {
      this.form = this.$form.createForm(this, {name: "normal_login"});
    },
    created() {
      this.getCode();
    },
    methods: {
      handleSubmit(e) {
        e.preventDefault();
        this.form.validateFields((err, values) => {
          if (!err) {
            console.log("Received values of form: ", values);
            this.login(values);
          }
        });
      },
      login(values) {
        this.$axios
                .post("/login/login", {
                  username: values.username,
                  password: values.password,
                  identifyCode: values.identifyCode,
                })
                .then((response) => {
                  console.log(response);
                })
                .catch((error) => {
                  console.log(error);
                });
      },
      getCode() {
        this.$axios
                .get("/login/verifyCode", {
                  responseType: "arraybuffer",
                })
                .then((response) => {
                  return (
                          "data:image/jpeg;base64," +
                          btoa(
                                  new Uint8Array(response.data).reduce(
                                          (data, byte) => data + String.fromCharCode(byte),
                                          ""
                                  )
                          )
                  );
                })
                .then((data) => {
                  this.identifyImg = data;
                })
                .catch((error) => {
                  console.log(error);
                });
      },
    },
  };
</script>
<style>
  #components-form-demo-normal-login .login-form {
    max-width: 300px;
  }

  #components-form-demo-normal-login .login-form-forgot {
    float: right;
  }

  #components-form-demo-normal-login .login-form-button {
    width: 100%;
  }
</style>


<!--
<template>
  <div class="columns">
    <div class="column is-three-fifths login-left">
      <img src="../assets/login/bloger.jpg" />
    </div>
    <form class="column login-right" :model="user" :rules="fieldRules">
      <div>
        <h1>欢迎登陆!</h1>
        <div class="field">
          <p class="control">
            <span style="margin-left: -15px">用户名：</span>
            <a-input v-model="user.username" style="width: 130px" />
            <span class="icon is-small">
              <i class="fa fa-home"></i>
            </span>
            <span class="icon is-small">
              <i class="fa fa-check"></i>
            </span>
          </p>
        </div>
        <div class="field">
          <p class="control">
            <span>密码：</span>
            <a-input
              v-model="user.password"
              style="width: 130px"
              type="password"
            ></a-input>
            <span class="icon is-small">
              <i class="fa fa-lock"></i>
            </span>
          </p>
        </div>
        <div class="field code-center">
          <div>
            <span style="margin-left: 80px">验证码：</span>
            <a-input
              v-model="user.identifyCode"
              type="text"
              style="width: 130px"
            />
          </div>
          <img :src="identifyImg" style="margin-left: 15px" @click="getCode" />
        </div>

        <div class="field" style="margin-top: 10px">
          <p class="control">
            <a-button type="Default" @click="login">登录</a-button>
          </p>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      user: {
        username: "",
        password: "",
        identifyCode: "",
      },
      identifyImg: "",
      fieldRules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
          {
            min: 10,
            max: 15,
            message: "长度在 2 到 15 个字符",
          }
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
        identifyCode: [
          {
            required: true,
            message: "请输入验证码",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getCode();
  },
  methods: {
    login(){
      this.$axios
        .post("/login/login", {
          username: this.user.username,
          password: this.user.password,
          identifyCode: this.user.identifyCode,
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getCode() {
      this.$axios
        .get("/login/verifyCode", {
          responseType: "arraybuffer",
        })
        .then((response) => {
          return (
            "data:image/jpeg;base64," +
            btoa(
              new Uint8Array(response.data).reduce(
                (data, byte) => data + String.fromCharCode(byte),
                ""
              )
            )
          );
        })
        .then((data) => {
          this.identifyImg = data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>


<style>

.code {
  width: 124px;
  height: 25px;
  border: 1px solid rgba(186, 186, 186, 1);
}

.code-center {
  display: flex;
  justify-content: center;
}

.login-code {
  cursor: pointer;
}
</style>-->
