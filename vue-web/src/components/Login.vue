<template>
    <section class="login-main">
        <div class="container">
            <div class="columns">
                <div class="column is-three-fifths login-left">
                    <img src="../assets/login/bloger.jpg">
                </div>
                <div class="column login-right" :rules="rules">
                    <div>
                        <h1>欢迎登陆!</h1>
                        <div class="field">
                            <p class="control">
                                <span style="margin-left: -15px">用户名：</span>
                                <input v-model="user.username" class="code" placeholder="用户名">
                                <span class="icon is-small is-left">
                          <i class="fa fa-home"></i>
                        </span>
                                <span class="icon is-small is-right">
                          <i class="fa fa-check"></i>
                        </span>
                            </p>
                        </div>
                        <div class="field">
                            <p class="control">
                                <span>密码：</span>
                                <input v-model="user.password" class="code" type="password" placeholder="密码">
                                <span class="icon is-small is-left">
                          <i class="fa fa-lock"></i>
                        </span>
                            </p>
                        </div>
                        <div class="field code-center">
                            <div>
                                <span style="margin-left: 80px">验证码：</span>
                                <input type="text" class="code" placeholder="验证码"/>
                            </div>
                            <img :src="verifyimg" style="margin-left: 15px;" @click="getCode"/>
                        </div>

                        <div class="field">
                            <p class="control">
                                <button @click="login()" type="submit" class="button is-success login-btn">
                                    登录
                                </button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>

    export default {
        name: "login",
        data() {
            return {
                user: {},
                verifyimg: "",
                rules: {
                    username: [{
                        required: true, message: "请输入用户名", trigger: 'blur'
                    }, {
                        min: 2,
                        max: 15,
                        message: '长度在 2 到 15 个字符'
                    }, /*{
                        pattern: /^[\u4E00-\u9FA5]+$/,
                        message: '用户名只能为中文'
                    }*/],
                    password: [
                        {required: true, message: "请输入密码", trigger: 'blur'}

                    ]
                }
            }
        },
        created() {
            this.getCode();
        },
        mounted() {
            this.user.identifyCode = "";
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
                    this.verifyimg = data;
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