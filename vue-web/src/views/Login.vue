<template>
<div class="w50">
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
        <a-icon slot="prefix" type="user" style="color: rgba(0, 0, 0, 0.25)" />
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
        <a-icon slot="prefix" type="lock" style="color: rgba(0, 0, 0, 0.25)" />
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
          <img :src="this.identifyImg" @click="getCode" />
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
</div>
</template>

<script>
export default {
  data() {
    return {
      identifyImg: "",
    };
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: "normal_login" });
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
        .post("/login", {
          username: values.username,
          password: values.password,
          identifyCode: values.identifyCode,
        })
        .then((response) => {
          localStorage.clear();
          localStorage.setItem("token", response.data.obj.token);
          this.$router.push({
            name: "Home",
          });
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getCode() {
      this.$axios
        .get("/verify/code", {
          responseType: "arraybuffer",
          headers: {
            "User-Token": "123213",
          },
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
.w50{
  width: 50%;
  margin: 100px auto 0;
}
</style>