<template>
    <section class="login-main">
        <div class="container">
            <div class="columns">
                <div class="column is-three-fifths login-left">
                    <img src="../assets/login/bloger.jpg">
                </div>
                <div class="column login-right" :rules="rules">
                    <div class="login-wrapper">
                        <h1>Let`s blog together!</h1>
                        <div class="field">
                            <p class="control has-icons-left has-icons-right">
                                <input v-model="user.username" class="input" placeholder="用户名">
                                <span class="icon is-small is-left">
                          <i class="fa fa-home"></i>
                        </span>
                                <span class="icon is-small is-right">
                          <i class="fa fa-check"></i>
                        </span>
                            </p>
                        </div>
                        <div class="field">
                            <p class="control has-icons-left">
                                <input v-model="user.password" class="input" type="password" placeholder="密码">
                                <span class="icon is-small is-left">
                          <i class="fa fa-lock"></i>
                        </span>
                            </p>
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
        methods: {
            login() {
                this.$axios.post('http://localhost:8084/login/login', {
                    username: this.user.username,
                    password: this.user.password
                }).then((response) => {
                    console.log(response);
                }).catch((error) => {
                    console.log(error);
                });

            }
        }
    }
</script>


<style scoped>

</style>