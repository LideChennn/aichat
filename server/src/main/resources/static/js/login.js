const app = new Vue({
    el: "#app",
    data: {
        user: {
            username: 'chenlide',
            password: '1234'
        },
        loginMessage:'',//用于给用户警告
    },
    methods: {
        gotoSignup() {
            location.href = `/pages/signup.html`//主页面
        },
        login() {
            //检查是否合法
            if (this.userIsValid()) {
                //后端记得保存用户信息到session
                axios.post("/user/login", this.user)
                    .then(resp => {
                        console.log(resp.data);
                        if (resp.data.code === 1) {
                            // 设置 Cookie
                            document.cookie = `authentication=${resp.data.data.authentication}; path=/`;

                            location.href = `/pages/chatgpt.html`//主页面
                        } else {
                            //result对象回复的消息记得有，提醒用户登录错误
                            this.loginMessage = resp.data.msg;
                        }
                    }).catch(error => {
                        console.error(error);
                        this.loginMessage = '登录失败,请检查您的账号密码是否正确';
                });
            } else {
                this.loginMessage = '账号密码不为空';
            }
        },
        userIsValid() {
            return !(this.user.username === '' || this.user.password === '');
        }
    }
});