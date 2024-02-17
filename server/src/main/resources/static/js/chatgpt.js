const socket = new SockJS('http://8.134.124.248/websocket');
let authentication = '';

const stompClient = Stomp.over(socket);
new Vue({
    el: "#app",
    data() {
        return {
            localUser: {},          // 本用户
            histories: [],           //历史记录
            selectedHistory: null,  //选择的历史记录,就是histories的下标
            messages: [],
            inputText: '',

            //弹窗
            modalVisible: false,
            historyInput: '',

            //打字
            textToType: "",
            typedText: "",
            index: 0,

            //websocket
            stompClient: stompClient,
            receivedMessages: {},

            //删除历史记录的小弹窗
            showConfirm: false,
            confirmIndex: -1,

            //删除全部历史记录的小弹窗
            showDeleteAllHistories: false,

            //编辑历史名字
            showEditHistoryName: false,
            editedHistoryName: '',

            //下拉框
            isDropdownVisible: false,
            options: [{
                promptId: 0,
                content: null,
                model: null,
                label: "自定义",
            }], //prompt，数据格式见实体类
            selectedOption: null,

            //机器人头像
            aiHeadImg: '../images/shiwaluo.png',
            userHeadImg: '../images/cxk.png',

            //检测窗口大小
            windowWidth: 0,

        };
    },
    async mounted() {
        authentication = this.getParameterByName('authentication');
        await this.getUser();

        //判断用户是否为空，若为空则跳转到登录页面
        if (Object.keys(this.localUser).length === 0) {
            location.href = `/pages/login.html`//主页面
            return;
        }

        //连接websocket
        this.connect();
        this.$nextTick(function () {
            window.addEventListener('resize', this.getWindowWidth);
            this.getWindowWidth();
        });

        await this.getAllHistory();

        if (this.windowWidth < 600) {
            this.options.splice(0, 1);
        }
        //初始化系统默认的prompt
        this.initPrompt();

        // 当点击下拉框以外的区域时，关闭下拉框
        this.outsideClickListener = (event) => {
            if (!$(event.target).closest('.dropdown').length) {
                this.isDropdownVisible = false;
            }
        };
        $(document).on('click', this.outsideClickListener);
    },
    beforeDestroy() {
        // 移除全局监听器
        $(document).off('click', this.outsideClickListener);
    },
    methods: {
        //选择历史记录，选择了应该更新聊天信息
        async selectHistory(index) {
            this.showConfirm = false;
            let _this = this;
            this.selectedHistory = index;
            //根据历史记录的id获取聊天记录
            await axios.get(`/record/${_this.histories[index].historyId}` ,{headers: {'authentication': authentication}})
                .then(response => {
                    if (response.data.code === 1) {
                        console.log(response.data.data);
                        _this.messages = _this.processChatRecords(response.data.data);
                        if (_this.histories[index].promptId === 4) {
                            _this.aiHeadImg = "../images/img.png"
                        } else {
                            _this.aiHeadImg = "../images/chatgpt.png"
                        }
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        },
        async getUser() {
            let _this = this;
            //发送ajax请求
            await axios.get('/user/getUser', {headers: {'authentication': authentication}}).then(response => {
                    // 请求成功时处理 response.data
                    // console.log(response.data.data);
                    if (response.data.code === 1) {
                        _this.localUser = response.data.data;
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        },
        async getAllHistory() {
            let _this = this;
            //根据id查询历史记录 避坑，字符串拼接用""比较好，不要用''
            await axios.get('/history' ,{headers: {'authentication': authentication}})
                .then(response => {
                    // 请求成功时处理 response.data
                    console.log(response.data.data);
                    if (response.data.code === 1) {
                        _this.histories = response.data.data;
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        },

        scrollToBottom() {
            this.$refs.messageContainer.scrollTop = this.$refs.messageContainer.scrollHeight;
        },
        //把后端的数据转化为前端messages的数据格式
        processChatRecords(chatRecords) {
            const messages = [];
            /*把后端的数据转化为前端messages的数据格式
            ChatRecord = [{
                recordId=1,
                historyId=1,
                content='你好',
                AiResponse = {
                    aiResponseId=1,
                    recordId=1,
                    content='您好，请问有什么需要帮助的吗'
               } ...]
             }
             */
            chatRecords.forEach((record) => {
                // 用户消息
                messages.push({
                    text: record.content,
                    texts: [],
                    isMine: true
                });
                // AI回应
                messages.push({
                    text: record.aiResponse.content,
                    texts: [],
                    isMine: false
                });
            });

            return messages;
        },
        showAddHistoryModal() {
            this.modalVisible = true;
            this.startTextToType("您输入的prompt将作为Ai的系统默认角色,请务必好好填写,不然会影响对话体验！输入为空则进入系统默认");
        },

        //新增历史记录，并且切换到新的历史记录
        async submitData() {
            let history;
            if (this.selectedOption.promptId === 0) {//自定义历史记录
                history = {
                    historyName: this.selectedOption.label,
                    userAiPrompt: this.historyInput,
                    promptId: null,
                }
            } else {
                history = {
                    historyName: this.selectedOption.label,
                    userAiPrompt: null,
                    promptId: this.selectedOption.promptId
                }
            }
            await axios.post("/history", history,{headers: {'authentication': authentication}}).then((res) => {
                //如果操作成功，关闭弹层，显示数据
                console.log(res.data.data);
                if (res.data.code === 1) {
                    history.historyId = res.data.data;
                }
            }).catch(error => {
                console.error(error);
            });

            this.histories.push(history);
            this.modalVisible = false;
            this.historyInput = '';
            await this.selectHistory(this.histories.length - 1);
        },
        cancel() {
            this.modalVisible = false;
            this.historyInput = '';
            this.stopTextToType();
        },

        //打字
        startTextToType(text) {
            this.textToType = text;
            this.typedText = "";
            this.index = 0;
            this.typeText();
        },
        stopTextToType() {
            this.textToType = "";
            this.typedText = "";
            this.index = 0;
        },
        typeText() {
            if (this.index < this.textToType.length) {
                this.typedText += this.textToType[this.index];
                this.index++;
                setTimeout(this.typeText, 100); // 调整打字速度
            }
        },

        //websocket
        connect() {
            //headers: 消息的头信息，表示为一个JavaScript对象。在这个例子中，我们传递一个空对象{}，表示没有额外的头信息。
            this.stompClient.connect({}, this.onConnected, this.onError);
        },
        onConnected() {
            this.stompClient.subscribe(`/user/${this.localUser.userId}/messages`, this.onMessageReceived);
        },
        onMessageReceived(payload) {
            this.receivedMessages.text += (JSON.parse(payload.body).content);
            // this.messages.push({text: JSON.parse(payload.body).content, isMine: false});
        },
        async sendMessage() {
            if (this.inputText.trim()) {
                let texts;//长文本
                if (this.histories[this.selectedHistory].promptId === 4 || this.histories[this.selectedHistory].promptId === 5) {
                    let msg = {
                        userinput: this.inputText.trim(),
                        nums: 4
                    }
                    await axios.post("http://localhost:5000/getSimilarityText",msg, {headers: {'authentication': authentication}}).then((res) => {
                        //如果操作成功，关闭弹层，显示数据
                        texts = res.data.messages;
                    }).catch(error => {
                        console.error(error);
                    });
                }
                if (this.histories[this.selectedHistory].promptId === 5) {
                    this.messages.push({text: this.inputText.trim(), texts: [], isMine: true});
                    this.messages.push({text: texts[0] + "\n" + texts[1], texts: texts, isMine: false});
                    this.inputText = '';
                    return;
                }
                this.messages.push({text: this.inputText.trim(), texts: [], isMine: true});
                this.stompClient.send(`/app/send`, {},
                    JSON.stringify(
                        {
                            userId: this.localUser.userId,
                            content: this.inputText.trim(), //用户输入信息
                            historyId: this.histories[this.selectedHistory].historyId,
                            promptId: this.histories[this.selectedHistory].promptId,
                            texts: texts //k个原文
                        }
                    ));
                this.inputText = '';
                this.receivedMessages = {
                    text: '',
                    texts: texts,
                    isMine: false
                }
                this.messages.push(this.receivedMessages);

                this.$nextTick(() => {
                    this.scrollToBottom();
                });
            }
        },
        onError() {
            console.error('WebSocket连接失败');
        },

        //删除历史记录
        deleteHistory() {
            axios.delete(`/history/${this.histories[this.selectedHistory].historyId}`,{headers: {'authentication': authentication}})
                .then((resp) => {
                }).catch(error => {
                console.error(error);
            });


            this.histories.splice(this.confirmIndex, 1);
            this.showConfirm = false;
            if (this.histories.length > 0) {
                // 如果还有历史记录，更新 selectedHistory 为下一个记录的索引，或在删除最后一个记录时为上一个记录的索引
                this.selectedHistory = Math.min(this.confirmIndex, this.histories.length - 1);
                this.selectHistory(this.selectedHistory);
            } else {
                // 如果没有历史记录，将 selectedHistory 设为 null
                this.selectedHistory = null;
            }
        },

        //删除所有历史
        showAndHideDeleteAllHistories() {
            this.showDeleteAllHistories = true;
            setTimeout(() => {
                this.showDeleteAllHistories = false;
            }, 3000); // 3000 毫秒 = 3 秒
        },
        async deleteAllHistories() {
            await axios.delete('/history/all',{headers: {'authentication': authentication}})
                .then((resp) => {

                }).catch(error => {
                    console.error(error);
                });

            this.showDeleteAllHistories = false;
            this.histories.splice(0, this.histories.length);
            this.selectedHistory = null;
        },

        //编辑历史名字
        toggleEditHistoryName() {
            this.showEditHistoryName = !this.showEditHistoryName;
            this.editedHistoryName = this.histories[this.selectedHistory].historyName;
        },
        updateHistoryName() {
            this.histories[this.selectedHistory].historyName = this.editedHistoryName;

            axios.put("/history",
                {
                    historyId: this.histories[this.selectedHistory].historyId,
                    historyName: this.histories[this.selectedHistory].historyName
                },{headers: {'authentication': authentication}}
            ).then(res => {

            }).catch(error => {
                console.error(error)
            })

            this.showEditHistoryName = false;
        },

        //下拉框方法
        toggleDropdown() {
            this.isDropdownVisible = !this.isDropdownVisible;
        },
        selectOption(option) {
            this.selectedOption = option;
            this.isDropdownVisible = false;
            console.log(this.selectedOption)
            if (this.selectedOption.promptId === 0) {
                this.showAddHistoryModal();
            }
        },

        //初始化prompt
        initPrompt() {

            axios.get("/prompt/", {headers: {'authentication': authentication}})
                .then(resp => {
                    console.log(resp.data);
                    if (resp.data.code === 1) {
                        this.options = this.options.concat(resp.data.data);
                        if (this.windowWidth <= 600)
                            this.selectedOption = this.options[0];
                        else
                            this.selectedOption = this.options[1];
                    }
                }).catch(e => {
                console.error(e);
            })

        },

        //在newChat页面发送信息
        async earlySendMsg() {
            if (this.selectedOption.promptId === 0) {
                alert('未自定义prompt!');
                return;
            }
            await this.submitData();
            await this.sendMessage();
        },


        //登录退出
        logout() {
            axios.get('/user/logout', {headers: {'authentication': authentication}})
                .then(resp => {
                    //设置过期时间，使得jwt cookie失效
                    document.cookie = `authentication=; expires=Thu, 01 Jan 1970 00:00:01 GMT; path=/`;
                    location.href = '/pages/login.html'
                })
        },

        //win窗口大小
        getWindowWidth(event) {
            this.windowWidth = document.documentElement.clientWidth;
        },
        beforeDestroy() {
            window.removeEventListener('resize', this.getWindowWidth);
        },

        //显示原始文本
        showContent(texts) {
            console.log(texts);
            axios.post("/user/save_texts", texts, {headers: {'authentication': authentication}}).then((res) => {
                //如果操作成功，关闭弹层，显示数据
                window.open('/pages/show_content.html', '_blank');
            }).catch(error => {
                console.error(error);
            });
        },

        // 获取 authenzation 参数
        getParameterByName(name) {
            var cookies = document.cookie.split('; ');
            var receivedData;
            cookies.forEach(function(cookie) {
                var parts = cookie.split('=');
                if (parts[0] === name) {
                    receivedData = parts[1];
                }
            })
            return receivedData;
        }

    },

});