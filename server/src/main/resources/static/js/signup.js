const app = new Vue({
    el: "#app",
    data: {
        user: {
            username: '',
            password: ''
        },
        confirmPassword: '',
        signupMessage: '',//用于给用户警告
    },
    methods: {
        gotoLogin() {
            location.href = '/pages/login.html'
        },
        signup() {
            //检查是否合法
            if (this.userIsValid()) {
                axios.post("/user/signup", this.user)
                    .then(resp => {
                        console.log(resp.data);
                        if (resp.data.code === 1) {
                            this.signupMessage = '注册成功！3秒后跳转登录页面';
                            setTimeout(() => {
                                location.href = '/pages/login.html'//主页面
                            }, 3000);
                        } else {
                            //result对象回复的消息记得有，提醒用户登录错误
                            this.signupMessage = resp.data.msg;
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        this.signupMessage = '系统繁忙，请稍后再试';
                    });
            }
        },
        userIsValid() {
            const flag1 =  !(this.user.username === '' || this.user.password === '' || this.confirmPassword === '');
            const flag2 = this.user.password === this.confirmPassword;
            if (!flag1) {
                this.signupMessage = '请完整填写表单';
            } else if (!flag2) {
                this.signupMessage = '两次输入的密码不一致！';
            }
            return flag1 && flag2;
        }
    }
});