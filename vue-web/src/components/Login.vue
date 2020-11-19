<template>
    <div class="columns">
        <div class="column is-three-fifths login-left">
            <img src="../assets/login/bloger.jpg">
        </div>
        <el-form class="column login-right" :model="user" :rules="fieldRules">
            <div>
                <h1>欢迎登陆!</h1>
                <div class="field">
                    <p class="control">
                        <span style="margin-left: -15px">用户名：</span>
                        <el-input v-model="user.username" style="width: 130px"/>
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
                        <el-input v-model="user.password" style="width: 130px" type="password"></el-input>
                        <span class="icon is-small">
                          <i class="fa fa-lock"></i>
                        </span>
                    </p>
                </div>
                <div class="field code-center">
                    <div>
                        <span style="margin-left: 80px">验证码：</span>
                        <el-input v-model="user.identifyCode" type="text" style="width: 130px"/>
                    </div>
                    <img :src="identifyImg" style="margin-left: 15px;" @click="getCode"/>
                </div>

                <div class="field" style="margin-top: 10px">
                    <p class="control">
                        <a-button type="Default" @click="login">登录</a-button>
                    </p>
                </div>
            </div>
        </el-form>
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
                    identifyCode: ""
                },
                identifyImg: "",
                fieldRules: {
                    username: [{
                        required: true, message: "请输入用户名", trigger: 'blur'
                    }, {
                        min: 10,
                        max: 15,
                        message: '长度在 2 到 15 个字符'
                    }, /*{
                        pattern: /^[\u4E00-\u9FA5]+$/,
                        message: '用户名只能为中文'
                    }*/],
                    password: [{
                        required: true, message: "请输入密码", trigger: 'blur'
                    }],
                    identifyCode: [{
                        required: true, message: "请输入验证码", trigger: 'blur'
                    }]
                }
            }
        },
        created() {
            this.getCode();
        },
        methods: {
            login() {
                this.$axios.post('/login/login', {
                    username: this.user.username,
                    password: this.user.password,
                    identifyCode: this.user.identifyCode
                }).then((response) => {
                    console.log(response);
                }).catch((error) => {
                    console.log(error);
                });

            },
            /**
             * 获取验证码
             * 将验证码写到login.html页面的id = verifyimg 的地方
             */
            getCode() {
                this.$axios.get('/login/verifyCode', {
                    responseType: 'arraybuffer'
                }).then((response) => {
                    return 'data:image/jpeg;base64,' + btoa(
                        new Uint8Array(response.data)
                            .reduce((data, byte) => data + String.fromCharCode(byte), '')
                    );
                }).then(data => {
                    this.identifyImg = data;
                }).catch((error) => {
                    console.log(error);
                });
            }
        }
    }
</script>


<style>
    /*验证码样式*/
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
</style>